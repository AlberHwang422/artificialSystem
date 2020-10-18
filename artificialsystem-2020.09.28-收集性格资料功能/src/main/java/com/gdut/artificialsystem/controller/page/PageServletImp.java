package com.gdut.artificialsystem.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class PageServletImp implements PageServlet{

    /**
     * 返回登录主界面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/")
    public String servlet(HttpServletRequest request, HttpServletResponse response){

        HttpSession session=request.getSession();
        Object obj=session.getAttribute("user");
        if(obj!=null)
            return "index";
        else
            return "login";
    }

    /**
     * 跳转到login.html
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/login.html")
    public String loginPage(HttpServletRequest request, HttpServletResponse response){

        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @Override
    @RequestMapping("/register.html")
    public String registerPage(HttpServletRequest request, HttpServletResponse response){
        return "register";
    }

    /**
     * 跳转重置密码界面
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/reset.html")
    public String resetPage(HttpServletRequest request, HttpServletResponse response){return "reset";}

    /**
     * 跳转到验证页面
     * @return
     */
    @Override
    @RequestMapping("/identitypage.html")
    public String identityPage(HttpServletRequest request, HttpServletResponse response){
        return "identitypage";
    }

    @RequestMapping("/characterform.html")
    @Override
    public String characterPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
        Object obj=session.getAttribute("user");
        if(obj==null){
            return "redirect:/";
        }
        else
            return "characterform";
    }
}
