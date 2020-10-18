package com.gdut.artificialsystem.service.user;

import com.gdut.artificialsystem.dao.user.UserFormMapper;
import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.StringUtil;
import com.gdut.artificialsystem.util.User;
import com.gdut.artificialsystem.util.UserHabitsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserFormMapper userFormMapper;
    /**
     * 处理生活习惯表单提交的处理逻辑
     * @param id
     * @param question1
     * @param question2
     * @param question3
     * @param question4
     * @param question5
     * @param question6
     * @return
     */
    @Override
    public Msg insertUserHabitForm(String id, String question1, String question2,
                                   String question3, String question4, String question5,
                                   String question6) {
        //1.判断输入是否合法
        if(StringUtil.isEmptyStr(id)||StringUtil.isEmptyStr(question1)||StringUtil.isEmptyStr(question2)
            ||StringUtil.isEmptyStr(question3)||StringUtil.isEmptyStr(question4)||StringUtil.isEmptyStr(question5)||
            StringUtil.isEmptyStr(question6)){

            return new Msg(0,"EMPTY INPUT",null);
        }
        if(StringUtil.notDigitalStr(question1)||StringUtil.notDigitalStr(question2)||StringUtil.notDigitalStr(question3)||
           StringUtil.notDigitalStr(question4)||StringUtil.notDigitalStr(question5)||StringUtil.notDigitalStr(question6)){
            return new Msg(0,"EMPTY INPUT",null);
        }

        //2.封装成UserForm
        UserHabitsForm form=new UserHabitsForm();
        form.setId(id);
        form.setAnswer1(Integer.valueOf(question1));
        form.setAnswer2(Integer.valueOf(question2));
        form.setAnswer3(Integer.valueOf(question3));
        form.setAnswer4(Integer.valueOf(question4));
        form.setAnswer5(Integer.valueOf(question5));
        form.setAnswer6(Integer.valueOf(question5));


        //3.调用dao，并返回结果
        int result= userFormMapper.insert(form);
        //3.1判断
        if(result>0)
            return new Msg(1,"SUCCESS",form);
        else
            return new Msg(0,"ERROR",form);
    }

    /**
     * 设置user属性，已填写habits收集表
     * @param user
     * @return
     */
    public Msg setUserIsSubmitCharacterForm(User user){
        //1.检查
        if(user.isSubmitForm())
            return new Msg(0,"You haved finished the form before",user);
        else
            user.setSubmitForm(true);

        //2.调用dao方法
        int result= userMapper.updateById(user);

        //3.检查
        if(result>0)
            return new Msg(1,"SUCCESS",user);
        else
            return new Msg(0,"ERROR",user);
    }
}
