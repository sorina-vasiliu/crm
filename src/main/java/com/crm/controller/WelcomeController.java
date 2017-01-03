/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.crm.controller;

import com.crm.model.datatable.HomeShallow;
import com.crm.model.entities.User;
import com.crm.security.Role;
import com.crm.service.HomeService;
import com.crm.service.UserService;
import com.crm.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class
WelcomeController {
    @Autowired
    UserService userService;
    @Autowired
    HomeService homeService;

    @RequestMapping("/home")
    public String home(ModelMap modelMap, @RequestParam(name = "type", required = false) String type) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        populateModelMap(modelMap, type);
        return "landing-page";
    }

    @RequestMapping(value = "/home/addWyswyg", method = RequestMethod.POST)
    public ResponseEntity<HomeShallow> addWyswyg(@RequestParam("htmlForWysiwyg") String htmlForWysiwyg) {
        HomeShallow response = homeService.addWywywig(StringEscapeUtils.escapeHtml4(htmlForWysiwyg));
        ResponseEntity<HomeShallow> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }

    @RequestMapping(value = "/home/updateWyswyg", method = RequestMethod.POST)
    public ResponseEntity<String> addWyswyg(@RequestParam("htmlForWysiwyg") String htmlForWysiwyg,
                                            @RequestParam(name = "id") int id) {
        homeService.updateWywywig(StringEscapeUtils.escapeHtml4(htmlForWysiwyg), id);
        ResponseEntity<String> entity = new ResponseEntity<String>(StringEscapeUtils.escapeHtml4(htmlForWysiwyg), HttpStatus.OK);
        return entity;
    }

    @RequestMapping(value = "/home/getHtmlContent", method = RequestMethod.GET)
    public ResponseEntity<List<HomeShallow>> getHtmlContent(@RequestParam(name = "usersId[]") List<Integer> usersId) {
        List<HomeShallow> home = homeService.getWywywig();
        ResponseEntity<List<HomeShallow>> entity = new ResponseEntity<>(home, HttpStatus.OK);
        return entity;
    }

    @RequestMapping(value = "/home/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(@RequestParam(name = "id") int id) {
        homeService.delete(id);
        ResponseEntity<Integer> entity = new ResponseEntity<Integer>(id, HttpStatus.OK);
        return entity;
    }

    private void populateModelMap(ModelMap modelMap, String type) {
        if (SessionUtils.GetCurrentUser().getRole() != Role.ADMIN) {
            List<String> content = homeService.getUserHome();
            modelMap.addAttribute("content", content);
            return;
        }

        if (type == null || type == "") {
            List<User> clients = userService.getAllClients();
            modelMap.addAttribute("data", clients);
            modelMap.addAttribute("activePage", "client");
            return;
        }
    }
}
