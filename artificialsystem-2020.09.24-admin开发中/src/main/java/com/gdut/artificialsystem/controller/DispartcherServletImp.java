package com.gdut.artificialsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispartcherServletImp implements DispartcherServlet{

    @RequestMapping("/*")
    @Override
    public String dispartLoginPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Object object=request.getSession().getAttribute("user");

        request.getRequestDispatcher("/index").forward(request,response);
        if(object==null)
            return "login";
        else
            return "index";
    }
}
