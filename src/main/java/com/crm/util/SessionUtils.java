package com.crm.util;

import com.crm.model.entities.User;
import com.crm.security.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;

import java.util.Collection;

/**
 * Utility class for managing session attributes and populating model
 * attributes.
 */
public class SessionUtils {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String USER_ROLE_ATTRIBUTE = "userRole";

    public static void populateModelWithAuthenticatedRole(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.size() == 1) {
            for (GrantedAuthority authority : authorities) {
                String authorityString = authority.getAuthority();
                if (authorityString.startsWith(ROLE_PREFIX)) {
                    authorityString = authorityString.substring(ROLE_PREFIX.length());
                }
                modelMap.addAttribute(USER_ROLE_ATTRIBUTE, authorityString);
                return;
            }
        } else {
            modelMap.addAttribute(USER_ROLE_ATTRIBUTE, Role.ADMIN.getRole());
        }
    }

    public static User GetCurrentUser() {
        User details = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return details;
    }
}
