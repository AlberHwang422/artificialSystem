package com.gdut.artificialsystem.service.admin;

import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService{

    @Autowired
    UserMapper userMapper;

    @Override
    public Msg addBatchStudentByExcel(User user) {

        //1.检查输入
        if(user.notRightInputUser())
            return new Msg(0,"WRONG INPUT",user);

        //2.调用dao方法
        try {
            int result=userMapper.insert(user);
            //3.检查
            //3.1插入成功
            if(result>0)
                return new Msg(1,"SUCCESS",user);
            else
                return new Msg(0,"SQL ERROR",user);
        }catch (Exception e){
            //3.3
            return new Msg(0,"SQL ERROR",user);
        }
    }
}
