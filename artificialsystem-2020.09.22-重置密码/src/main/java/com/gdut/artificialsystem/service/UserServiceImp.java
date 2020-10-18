package com.gdut.artificialsystem.service;

import com.gdut.artificialsystem.dao.UserDao;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.StringUtil;
import com.gdut.artificialsystem.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDaoImp;

    /**
     *  登录逻辑
     * @param  uid, upass
     * @return
     */
    @Override
    public Msg loginService(String uid,String upass) {

        User user=new User();
        user.setId(Integer.valueOf(uid));
        user.setPassword(upass);


        //1.判断输入
        if (StringUtil.isEmptyStr(uid) || StringUtil.isEmptyStr(upass)) {
            Msg msg=new Msg("用户输入为空",user);
            msg.setStatusCode(0);

            return msg;
        }

        //2.调用dao,进行数据库查询
        User real=userDaoImp.selectUserById(user.getId());

        //3.先判断查出来的用户是否为空
        if(real==null){
            Msg msg=new Msg("用户不存在",null);
            msg.setStatusCode(0);

            return msg;
        }
        //4.判断账户密码是否与表单数据一致
        //4.1 一致
        else if(uid.equals(""+real.getId())&&upass.equals(real.getPassword())){
            Msg msg=new Msg("登陆成功",real);
            msg.setStatusCode(1);

            return msg;
        }

        //4.2 不一致,登陆失败
        else{
            Msg msg=new Msg("密码错误",user);
            msg.setStatusCode(0);

            return msg;
        }
    }

    /**
     * msg.status=1 success
     * msg.status=0 fail]\\
     *注册逻辑
     * @param uname
     * @param upass
     * @param upass_confirm
     * @param uidentity
     * @return
     */
    @Override
    public Msg registerService(String uname, String upass, String upass_confirm,String uidentity) {
        //1.检查两个密码是否一致
        //1.1判断输入是否为空
        if(StringUtil.isEmptyStr(uname)||StringUtil.isEmptyStr(upass)||StringUtil.isEmptyStr(uidentity)||StringUtil.isEmptyStr(upass_confirm)){
            return new Msg(0,"请正确输入信息!",null);
        }
        if(!upass.equals(upass_confirm)){
            return new Msg(0,"请确保密码一致!",null);
        }
        //2. new user对象
        User user=new User();
        user.setName(uname);
        user.setPassword(upass);
        user.setIdentityid(uidentity);
        //3.调用dao层方法
        return userDaoImp.addUserDao(user);
    }

    @Override
    public Msg getLastPrimaryKey(){
        return userDaoImp.selectLastPrimaryKey();
    }

    /**
     *
     * 检验uid和uidentity
     * @param uid
     * @param uidentity
     * @return
     */
    public Msg checkIdentity(String uid,String uidentity){
        //1.输入验证
        if(StringUtil.notDigitalStr(uid)){
            return new Msg(0,"请正确输入账号id",null);
        }
        if(StringUtil.isEmptyStr(uid)||StringUtil.isEmptyStr(uidentity)){
            return new Msg(0,"输入错误",null);
        }
        //2.调用dao方法
        User user=userDaoImp.selectUserById(Integer.valueOf(uid));
        //3.判断
        //3.1 user为空
        if(user==null){
            return new Msg(0,"该用户不存在",null);
        }
        //3.2 identiy匹配
        if(user.getIdentityid().equals(uidentity)){
            return new Msg(1,"success",user);
        }
        //3.3
        else{
            return new Msg(0,"验证信息不正确",null);
        }
    }

    /**
     * 重置逻辑
     * @param uid
     * @param upass
     * @return
     */
    public Msg updataPasswordService(String uid,String upass,String upass_con){
        //1.输入验证
        if(StringUtil.isEmptyStr(upass)||StringUtil.isEmptyStr(uid)||StringUtil.isEmptyStr(upass_con))
            return new Msg(0,"请正确输入",null);
        if(!upass.equals(upass_con))
            return new Msg(0,"密码不一致",null);
        //2.调用dao
        Integer id=Integer.valueOf(uid);
        return userDaoImp.updataPassword(id,upass);
    }
}
