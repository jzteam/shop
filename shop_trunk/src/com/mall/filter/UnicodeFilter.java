package com.mall.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnicodeFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpServletRequest httpreq = (HttpServletRequest) request;
        HttpServletResponse httpresp = (HttpServletResponse) response;
        // 允许跨域请求
        httpresp.addHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(httpreq, httpresp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
