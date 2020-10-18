package com.gdut.artificialsystem.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;

public interface UserServlet {

    /**
     * 提交收集信息的表单(characterForm.html)
     * @param request
     * @param response
     * @return
     */
    public String submitCharacterForm(HttpServletRequest request, HttpServletResponse response);
}
