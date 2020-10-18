package com.gdut.artificialsystem.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserServlet {

    /**
     * loginServlet
     * 处理来自客户端的登录请求
     * @param request
     * @param response
     * @return
     */
    public String loginServlet(HttpServletRequest request, HttpServletResponse response);

    /**
     * 处理客户端的注册请求
     * @param request
     * @param response
     * @return
     */
    public String registerServlet(HttpServletRequest request, HttpServletResponse response);

    /**
     * 负责找回密码请求
     * @param request
     * @param response
     * @return
     */
    public String retrievePasswordServlet1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * 处理重置密码请求
     * 先验证sessio中有无uid
     * @param request
     * @param response
     * @return
     */
    public String resetServlet(HttpServletRequest request, HttpServletResponse response);

    /**
     * 跳转到验证页面
     * @return
     */
    public String identityPage(HttpServletRequest request, HttpServletResponse response);

    /**
     * 跳转到注册页面
     * @return
     */
    public String registerPage(HttpServletRequest request, HttpServletResponse response);

    /**
     * 跳转重置密码界面
     * @param request
     * @param response
     * @return
     */
    public String resetPage(HttpServletRequest request, HttpServletResponse response);
}
