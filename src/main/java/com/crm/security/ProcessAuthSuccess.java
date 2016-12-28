package com.crm.security;

import com.crm.model.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dragos on 4/11/2016.
 */
@Component
public class ProcessAuthSuccess extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getDetails();

        if (user.isFirstLogin()) {
            response.sendRedirect("/changepassword");
        }
        else{
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
