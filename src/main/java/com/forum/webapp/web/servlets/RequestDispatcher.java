package com.forum.webapp.web.servlets;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

public class RequestDispatcher extends DispatcherServlet implements Servlet {

    private static final long serialVersionUID = 5813812279964043014L;

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.doService(request, response);
    }

}
