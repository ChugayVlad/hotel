package com.my.controller.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private String defaultEncoding = "utf-8";
    private static final String ENCODING = "encoding";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter(ENCODING);
        if(encoding != null){
            defaultEncoding = encoding;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(defaultEncoding);
        response.setCharacterEncoding(defaultEncoding);

        chain.doFilter(request, response);
    }
}
