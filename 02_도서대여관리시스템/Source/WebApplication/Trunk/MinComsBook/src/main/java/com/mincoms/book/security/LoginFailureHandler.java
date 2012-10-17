package com.mincoms.book.security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        
        if(request.getHeader("X-Ajax-call")!=null){
        	 if (request.getHeader("X-Ajax-call").equals("true")) {
                 response.getWriter().print("LoginFailed");
                 response.getWriter().flush();
             }
        }
        else{
        	response.sendRedirect(request.getContextPath() + "/login?error=true");
        }
        
         
    }
}