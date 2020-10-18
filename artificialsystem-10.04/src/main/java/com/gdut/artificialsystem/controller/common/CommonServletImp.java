package com.gdut.artificialsystem.controller.common;

import com.gdut.artificialsystem.service.Common.CommonService;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class CommonServletImp implements CommonServlet{

    @Autowired
    CommonService commonServiceImp;

    /**
     * 处理登录请求
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/login")
    public String loginServlet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
        String uid=request.getParameter("uid");
        String upassword=request.getParameter("upass");

        Msg msg= commonServiceImp.loginService(uid,upassword);

        //1.查看登录是否成功
        //1.1登陆成功，去到主页
        if(msg.getStatusCode()==1){
            //HttpSession session=request.getSession();
            //设置user存在时间为两个小时
            User user= (User) msg.getObject();
            session.setMaxInactiveInterval(60*60*2);
            session.setAttribute("user",msg.getObject());

            if(user.getStatus()==1)
                return "userindex";
            else if(user.getStatus()==0)
                return "adminindex";
        }

        //1.2登录失败,回到原页面返回信息
        request.setAttribute("uid",uid);
        request.setAttribute("upass",upassword);
        request.setAttribute("tips",msg.getMessage());
        return "login";

    }

    /**
     * 处理登出请求
     * 跳转到 login.html页面
     * @param request
     * @param response
     * @param session
     * @return
     */
    @Override
    @RequestMapping("/logoff")
    public String logOffServlet(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Object user=session.getAttribute("user");
        if(user!=null)
            session.removeAttribute("user");
        return "login";
    }

    /**
     * 处理注册逻辑
     *
     * 还缺注册成功的页面
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/register")
    public String registerServlet(HttpServletRequest request, HttpServletResponse response){

        //1.前台获取参数
        String uname=request.getParameter("uname");
        String upass=request.getParameter("upass");
        String upass_confirm=request.getParameter("upass_confirm");
        String identity=request.getParameter("uidentity");

        //2.查看邮箱是否已经存在
        Msg check_email=commonServiceImp.checkIsReapeatEmail(identity);
        if(check_email.getStatusCode()==0)
        {
            request.setAttribute("tips",(String)check_email.getMessage());
            return "register";
        }

        //3.调用注册逻辑
        Msg msg=commonServiceImp.registerService(uname,upass,upass_confirm,identity);

        //4.注册成功
        if(msg.getStatusCode()==1)
        {
            User user= (User) msg.getObject();
            request.setAttribute("tips","你的账号是: "+user.getId());
            return "register";
        }
        //2.注册失败
        else{
            request.setAttribute("tips",msg.getMessage());
            return "register";
        }
    }

    /*                       处理重置密码

        1.发起请求============>>>2.验证身份============>>>3.重置密码

                                                                    */

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

        Msg msg=commonServiceImp.checkIdentity(uid,uidentiy);
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
            // return "redirect:identitypage.html";
            return "identitypage";
        }

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
        HttpSession session=request.getSession();

        Cookie[] cookies=request.getCookies();
        String cookie_uid = null;
        for(Cookie cookie:cookies){
            String name=cookie.getName();
            if(name.equals("uid"))
                cookie_uid=cookie.getValue();
        }

//        HttpSession session=request.getSession();
        Object uid=session.getAttribute("uid");

        if(!cookie_uid.equals(uid)){
            request.setAttribute("tips","error");
            return "reset";
        }

        //1.获取参数
        String upass=request.getParameter("newpass");
        String upass_con=request.getParameter("newpass_con");

        //2.调用service
        Msg msg=commonServiceImp.updataPasswordService((String) uid,upass,upass_con);

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

}
