package com.gdut.artificialsystem.controller.page;

import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class PageServletImp implements PageServlet{

    @Autowired
    UserMapper commonMapper;

    @RequestMapping("/test")
    public String testfun()
    {

        return null;
    }
    @RequestMapping("/test.html")
    public String test(HttpServletRequest request, HttpServletResponse response){
        User user=new User();
        user.setId(Long.valueOf("2"));
        user.setIdentityid("1397762176@qq.com");
        user.setPassword("asdf");
        user.setName("Mage");
        int result= commonMapper.updateById(user);
        System.out.println(result);
        System.out.println(user);
        return "adminindex";
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
