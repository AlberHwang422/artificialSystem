package com.gdut.artificialsystem.service.user;

import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.User;

public interface UserService {



    /**
     *  登录逻辑
     * @param  uid, upass
     * @return
     */
    public Msg loginService(String uid,String upassword);

    /**
     * msg.status=1 success
     * msg.status=0 fail]
     *注册逻辑
     * @param uname
     * @param upass
     * @param upass_confirm
     * @param uidentity
     * @return
     */
    public Msg registerService(String uname,String upass,String upass_confirm,String uidentity);

    /**
     * 返回最新插入表的主键
     * @return
     */
    public Msg getLastPrimaryKey();

    /**
     * 检查用户id和身份验证
     * @param uid
     * @param uidentity
     * @return
     */
    public Msg checkIdentity(String uid,String uidentity);

    /**
     * 重置逻辑
     * @param uid
     * @param upass
     * @return
     */
    public Msg updataPasswordService(String uid,String upass,String upass_con);

}
