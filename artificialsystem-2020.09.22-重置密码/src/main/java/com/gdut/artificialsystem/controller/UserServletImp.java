package com.gdut.artificialsystem.controller;


import com.gdut.artificialsystem.service.UserService;
import com.gdut.artificialsystem.service.UserServiceImp;
import com.gdut.artificialsystem.util.Msg;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpCookie;


@Controller
public class UserServletImp implements UserServlet{

    @Autowired
    UserService userServiceImp;

    /**
     * test方法
     * @param request
     * @param response
     * @return
     */


    /**
     * 返回登录主界面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/")
    public String test(HttpServletRequest request, HttpServletResponse response){

        return "login";
    }


    /**
     * 处理登录请求
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/login")
    public String loginServlet(HttpServletRequest request, HttpServletResponse response) {

        String uid=request.getParameter("uid");
        String upassword=request.getParameter("upass");

        Msg msg= userServiceImp.loginService(uid,upassword);

        //1.查看登录是否成功
        //1.1登陆成功，去到主页
        if(msg.getStatusCode()==1){
            HttpSession session=request.getSession();
            session.setMaxInactiveInterval(60*60*2);
            session.setAttribute("user",msg.getObject());

            return "index";
        }

        //1.2登录失败,回到原页面返回信息
        else {
            request.setAttribute("uid",uid);
            request.setAttribute("upass",upassword);
            request.setAttribute("tips",msg.getMessage());
            return "login";
        }
    }

    /**
     * 处理注册逻辑
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/register")
    public String registerServlet(HttpServletRequest request, HttpServletResponse response){
        String uname=request.getParameter("uname");
        String upass=request.getParameter("upass");
        String upass_confirm=request.getParameter("upass_confirm");
        String identity=request.getParameter("uidentity");

        Msg msg=userServiceImp.registerService(uname,upass,upass_confirm,identity);

        //1.注册成功
        if(msg.getStatusCode()==1)
        {
            Msg lastPriamryKey=userServiceImp.getLastPrimaryKey();
            request.setAttribute("register_id",lastPriamryKey.getObject());
            return "index";
        }
        //2.注册失败
        else{
            request.setAttribute("tips",msg.getMessage());
            return "register";
        }
    }

    /**
     * 负责找回密码请求
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/forget")
    public String retrievePasswordServlet1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid=request.getParameter("uid");
        String uidentiy=request.getParameter("uidentity");

        Msg msg=userServiceImp.checkIdentity(uid,uidentiy);
        if(msg.getStatusCode()==1)
        {
            HttpSession session=request.getSession();
            session.setAttribute("uid",uid);
            request.setAttribute("uid","id:"+uid+"  密码重置");
            Cookie cookie=new Cookie("uid",uid);
            cookie.setMaxAge(10*60);
            response.addCookie(cookie);
            return "reset";

        }
        else{
            request.setAttribute("tips",msg.getMessage());
//            return "identitypage";
        }
        return null;
    }

    /**
     * reset.html可以处理 ”/reset请求“
     * 处理重置密码请求
     * 先验证sessio中有无uid
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/reset")
    @Override
    public String resetServlet(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies=request.getCookies();
        String cookie_uid = null;
        for(Cookie cookie:cookies){
            String name=cookie.getName();
            if(name.equals("uid"))
                cookie_uid=cookie.getValue();
        }

        HttpSession session=request.getSession();
        Object uid=session.getAttribute("uid");

        if(!cookie_uid.equals(uid)){
            request.setAttribute("tips","error");
            return "reset";
        }

        //1.获取参数
        String upass=request.getParameter("newpass");
        String upass_con=request.getParameter("newpass_con");

        //2.调用service
        Msg msg=userServiceImp.updataPasswordService((String) uid,upass,upass_con);

        //3.检查statuscode
        //3.1 statuscode==1
        if(msg.getStatusCode()==1){
            return "login";
        }
        //3.2statuscode==0
        else{
            request.setAttribute("tips",msg.getMessage());
            return "reset";
        }
    }

    /**
     * 跳转到验证页面
     * @return
     */
    @Override
    @RequestMapping("/identitypage.html")
    public String identityPage(HttpServletRequest request, HttpServletResponse response){
        return "identitypage";
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

}
