package com.crm.controller;

import com.crm.service.UserService;
import com.crm.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Dragos on 4/11/2016.
 */
@Controller
public class ChangePasswordController {

    @Autowired
    UserService userServices;

    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public String changepassword(ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        return "changePassword-page";
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changepassword(@RequestParam("password") String password, @RequestParam("confirmedPassword") String confirmedPassword, ModelMap modelMap) {
        if (!password.equals(confirmedPassword)) {
            modelMap.addAttribute("error", true);
            return "changePassword-page";
        }

        userServices.changePassword(password);
        return "redirect:/home";
    }
}
