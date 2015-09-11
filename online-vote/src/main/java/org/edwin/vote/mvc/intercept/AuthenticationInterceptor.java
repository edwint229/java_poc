package org.edwin.vote.mvc.intercept;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edwin.vote.mvc.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    private static final String DEFAULT_EXCLUDE_PATTERN = "(.*sendPassword.do$)|(.*login.do$)|(.*processLogin.do$)";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (Pattern.matches(DEFAULT_EXCLUDE_PATTERN, uri)) {
            return true;
        }

        User userData = (User) request.getSession().getAttribute(LoginController.SESSION_ID_USERINFO);
        if (null == userData) {
            logger.info("Unauthorized User, will redirect to login page");
            response.sendRedirect("login.do");
            return false;
        }

        if (uri.endsWith("loggedIn.do")) {
            logger.info("User [{}], Role [{}] Login Successfully!", userData.getUsername(), userData.getAuthorities().iterator()
                    .next().getAuthority());
        } else if (uri.endsWith("logout.do")) {
            logger.info("User [{}] Logout Successfully!", userData.getUsername());

            // clear Session
            request.getSession().setAttribute(LoginController.SESSION_ID_USERINFO, null);
        }

        return true;
    }

}
