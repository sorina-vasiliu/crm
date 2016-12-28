package com.crm.controller;

import com.crm.model.entities.User;
import com.crm.service.UserService;
import com.crm.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Dragos on 4/7/2016.
 */
@Controller
public class UserDetailsController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/clientDetails", method = RequestMethod.GET)
    public String getClientDetails(@RequestParam("id") int id, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        User client = userService.getClientById(id);
        modelMap.put("client", client);

        return "client-details-page";
    }
}
