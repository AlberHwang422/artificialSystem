package com.gdut.artificialsystem.dao.others;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.pojo.User;


public interface UserDao {

    public User selectUserById(Long id);

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
