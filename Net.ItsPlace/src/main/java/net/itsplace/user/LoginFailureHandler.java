package net.itsplace.user;


import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        logger.info("LoginFailureHander was Execute ..." + e.getMessage());
        System.out.println("/user/login 실패시--------------------"+request.getHeader("X-Ajax-call"));
        if(request.getHeader("X-Ajax-call")!=null){
        	 if (request.getHeader("X-Ajax-call").equals("true")) {
                 response.getWriter().print("error");
                 response.getWriter().flush();
             }
        }
        else{
        	response.sendRedirect(request.getContextPath() + "/login?error=true");
        }
        
        
    }
}