package com.gdut.artificialsystem.controller.user;

import com.gdut.artificialsystem.service.user.UserService;

import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserServletImp implements UserServlet {

    @Autowired
    UserService userServiceImp;

    @RequestMapping("/submitcharacterForm")
    @Override
    /**
     * 提交收集信息的表单(characterForm.html)
     * @param request
     * @param response
     * @return
     */
    public String submitCharacterForm(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");

        if(user==null)
            return "redirect:login.html";
        else if(user.isSubmitForm()){
            request.setAttribute("tips","You haved finished the form before");
            return "characterform";
        }

        //1.获取前台传过来的参数
        String uid=user.getId().toString();
        String question1=request.getParameter("question1");
        String question2=request.getParameter("question2");
        String question3=request.getParameter("question3");
        String question4=request.getParameter("question4");
        String question5=request.getParameter("question5");
        String question6=request.getParameter("question6");

        //2.调用service
        Msg msg= userServiceImp.insertUserHabitForm(uid,question1,question2,question3,
                                               question4,question5,question6);

        //3.检查结果
        //3.1设置用户issumbit属性
        if(msg.getStatusCode()==1)
        {
            userServiceImp.setUserIsSubmitCharacterForm(user);
            request.setAttribute("tips","Successfully submit!");
            return "userindex";
        }
        else
            return "characterform";
    }
}
