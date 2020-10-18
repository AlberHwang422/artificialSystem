package com.gdut.artificialsystem.service.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.pojo.User;
import com.gdut.artificialsystem.util.StringUtil;
import com.gdut.artificialsystem.util.UserPageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService{

    @Autowired
    UserMapper userMapper;



    /**
     * 使用excel表
     * 把学生名单添加到数据库
     * @param user
     * @return
     */
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

    /**
     * 查询未完成表格填写的学生名单
     * @return
     */
    @Override
    public Msg selectUnfinishedFromStudent(String currentPage,String limitcount) {

        //1.检查输入
        if(StringUtil.notDigitalStr(currentPage)||StringUtil.notDigitalStr(limitcount))
            return new Msg(0,"Wrong input!",null);

        //2.封装
        Integer curPage=Integer.valueOf(currentPage);
        Integer limit=Integer.valueOf(limitcount);

        Page<User> page=new Page<>(curPage,limit);
        EntityWrapper<User> condition=new EntityWrapper<>();

        //3.注入条件
        condition.eq("is_submit_characterform","0").gt("id",2);

        //4.调用dao
        List<User> resultlist=userMapper.selectPage(page,condition);
        Integer count=userMapper.selectList(condition).size();

        //5.封装
        UserPageUtil userPageUtil=new UserPageUtil(resultlist,count);

        //6.返回
        return new Msg(1,"Success",userPageUtil);
    }
}
