package com.wemjingwang.demo1.filter;

import com.wemjingwang.demo1.domain.Seller;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Order(1)
@WebFilter(filterName="urlFilter",urlPatterns= {"/api/*"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request1= (HttpServletRequest) servletRequest;
        HttpServletResponse response1= (HttpServletResponse) servletResponse;
        HttpSession session=request1.getSession();
        Seller seller= (Seller) session.getAttribute("seller");
        if(seller==null) {

            response1.sendRedirect("/");
        }
        filterChain.doFilter(request1, response1);
    }

    @Override
    public void destroy() {

    }
}
