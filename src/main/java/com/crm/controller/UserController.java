package com.crm.controller;

import com.crm.model.entities.User;
import com.crm.service.UserService;
import com.crm.util.DataTableRequest;
import com.crm.util.DataTableResponse;
import com.crm.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String getAllUsers(ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        return "client-page";
    }

    @RequestMapping(value = "/filterClients", method = RequestMethod.GET)
    @ResponseBody
    public DataTableResponse getUsers(@ModelAttribute DataTableRequest criteria, @RequestParam(name = "agentId", required = false) Integer agentId) {
        return userService.getDataTableResponse(criteria);
    }

    @RequestMapping(value = "/createClient", method = RequestMethod.GET)
    public String loadCreateUserPage(ModelMap modelMap, boolean error) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        //List<Agent> agents = agentService.getAllAgents();
        //modelMap.put("agents", agents);
        if (error) {
            modelMap.put("error", error);
        }
        return "client-create-page";
    }

    @RequestMapping(value = "/createClient", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("client") User client, ModelMap modelMap) {
        if (userService.createUser(client))
            return "redirect:/clients";

        return loadCreateUserPage(modelMap, true);
    }
}
