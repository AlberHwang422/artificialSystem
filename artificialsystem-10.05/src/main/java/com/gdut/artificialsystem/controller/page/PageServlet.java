package com.gdut.artificialsystem.controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface PageServlet {


    /**
     * admintables.html
     * @param session
     * @return
     */
    public String admintablesPage(HttpSession session);

    /**
     * 跳转用户到主页
     * @param session
     * @return
     */
    public String userIndexPage(HttpSession session);

    /**
     * 跳转到管理主页
     * @param session
     * @return
     */
    public String adminIndexPage(HttpSession session);

    /**
     * 跳转到login.html
     * @param request
     * @param response
     * @return
     */
    public String loginPage(HttpServletRequest request, HttpServletResponse response);

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

    /**
     * 跳转到收集性格页面
     * @return
     */
    public String characterPage(HttpServletRequest request, HttpServletResponse response);
}
