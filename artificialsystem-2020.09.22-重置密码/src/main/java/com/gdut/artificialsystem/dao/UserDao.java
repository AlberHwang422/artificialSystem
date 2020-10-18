package com.gdut.artificialsystem.dao;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.User;


public interface UserDao {

    public User selectUserById(Integer id);

    public Msg addUserDao(User user);

    public Msg selectLastPrimaryKey();

    /**
     * 数据库中更新数据---密码
     * @param uid
     * @param password
     * @return
     */
    public Msg updataPassword(Integer uid,String password);
}
