package com.gdut.artificialsystem.service.common;

import com.gdut.artificialsystem.pojo.User;
import com.gdut.artificialsystem.util.Msg;

public interface CommonService {

    /**
     * 重置密码逻辑
     * @param userId
     * @param userPassword
     * @param identity
     * @return
     */
    public Msg resetPassword(String userId,String userPassword,String identity);

    /**
     *  登录逻辑
     * @param  uid, upass
     * @return
     */
    public Msg loginService(String uid, String upassword);

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
     * 查看email是否已经存在
     * @param check_email
     * @return
     */
    public Msg checkIsReapeatEmail(String check_email);

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
