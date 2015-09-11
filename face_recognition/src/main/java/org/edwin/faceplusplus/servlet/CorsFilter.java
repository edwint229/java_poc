package org.edwin.faceplusplus.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;
        if (hreq.getMethod().equalsIgnoreCase("OPTIONS")) {
            HttpServletResponse hrep = (HttpServletResponse) response;
            // 授权的源控制
            hrep.setHeader("Access-Control-Allow-Origin", "*");
            // 授权的时间, 秒
            hrep.setHeader("Access-Control-Max-Age", "86400");
            // 授权的时间
            hrep.setHeader("Access-Control-Allow-Credentials", "true");
            // 允许请求的HTTP Method
            hrep.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
            // 控制哪些header能发送真正的请求
//            hrep.setHeader("Access-Control-Allow-Headers", "tk");
            hrep.setStatus(204);
        } else {
            HttpServletResponse hrep = (HttpServletResponse) response;
            // 授权的源控制
            hrep.setHeader("Access-Control-Allow-Origin", "*");
            // 授权的时间, 秒
            hrep.setHeader("Access-Control-Max-Age", "86400");
            // 授权的时间
            hrep.setHeader("Access-Control-Allow-Credentials", "true");
            // Tell the browser which response headers the JS can see, besides
            // the
            // "simple response headers"
//            hrep.setHeader("Access-Control-Expose-Headers", "tk");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
