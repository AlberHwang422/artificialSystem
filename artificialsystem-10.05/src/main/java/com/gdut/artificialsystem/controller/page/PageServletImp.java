package com.gdut.artificialsystem.controller.page;

import com.alibaba.fastjson.JSON;
import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.pojo.User;
import com.gdut.artificialsystem.util.UserPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class PageServletImp implements PageServlet{


    @Autowired
    UserMapper commonMapper;


    @ResponseBody
    @RequestMapping("/test")
    public String testfun(HttpServletRequest request) throws IOException
    {

        String pageno=request.getParameter("page");
        System.out.println(pageno);
        UserPageUtil userPageUtil=new UserPageUtil();
        User user1=new User();
        user1.setName("111");
        user1.setId("1111154645");
        User user2=new User();
        user2.setName("222");
        userPageUtil.add(user1);
        userPageUtil.add(user2);

        for(long i=0;i<40;i++){
            User user=new User();
            user.setId(Long.valueOf(i));
            userPageUtil.add(user);
        }
        String json= JSON.toJSONString(userPageUtil);
        return json;
    }


    @RequestMapping("/test.html")
    public String test() {

        return "test";
    }
    /**
     * 返回登录主界面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/")
    public String servlet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");

        if(user!=null){
            if(user.getStatus()==1)
                return "userindex";
            else
                return "adminindex";
        }
        else
            return "login";

    }

    @Override
    @RequestMapping("/userindex.html")
    public String userIndexPage(HttpSession session) {
        User user= (User) session.getAttribute("user");
        if(user==null)
            return "redirect:login.html";
        if(user.getStatus()==1)
            return "userindex";
        return "redirect:login.html";
    }

    /**
     * admintables.html
     * @param session
     * @return
     */
    @Override
    @RequestMapping("/admintables.html")
    public String admintablesPage(HttpSession session)
    {
        User user= (User) session.getAttribute("user");
        if(user==null)
            return "redirect:login.html";
        else if(user.getStatus()!=0)
            return "redirect:login.html";
        return "admintables";
    }

    @Override
    @RequestMapping("/adminindex.html")
    public String adminIndexPage(HttpSession session) {
        User user= (User) session.getAttribute("user");
        if(user==null)
            return "redirect:login.html";
        if(user.getStatus()==0)
            return "adminindex";
        return "redirect:login.html";
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
