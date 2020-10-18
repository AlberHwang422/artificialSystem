package com.gdut.artificialsystem.dao.others;

import com.gdut.artificialsystem.dao.others.UserDao;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 用主键ID，查找数据库的数据。
     * @param id
     * @return
     */
    @Override
    public User selectUserById(Long id) {
        String sql="select* from user where id=?";

        Object[] arg=new Object[1];
        arg[0]=id;
        User user;
        try {
            List<User> result=jdbcTemplate.query(sql,arg, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user=new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setImg(resultSet.getString("img"));
                    user.setPassword(resultSet.getNString("password"));
                    user.setCreatTime(resultSet.getDate("creatTime"));
                    user.setStatus(resultSet.getInt("status"));
                    user.setIdentityid(resultSet.getString("identityid"));
                    return user;
                }
            });

            user=result.get(0);
        }
        catch(Exception e){
            user=null;
            return user;
        }

        return user;
    }

    /**
     * 往数据库添的user表插入数据
     * name,password,img.status,id,creatTime
     * @param user
     * @return
     */
    @Override
    public Msg addUserDao(User user) {

        try {
            String sql="insert into user (name,password,status,identityid) values (?,?,?,?)";
            int result=jdbcTemplate.update(sql,user.getName(),user.getPassword(),1,user.getIdentityid());
            if(result>0){
                Msg last =selectLastPrimaryKey();
                if(last.getStatusCode()==1) {
                    user.setId((Long) last.getObject());
                    return new Msg(1, "添加成功", user);
                }
                else{
                    return new Msg(0,"添加失败",null);
                }
            }
            else
                return new Msg(0,"添加失败",null);
        }catch (Exception e){
            return new Msg(0,"添加失败",null);
        }

    }

    /**
     * 返回最新插入数据的主键
     * @return
     */
    @Override
    public Msg selectLastPrimaryKey(){
        String sql="SELECT LAST_INSERT_ID()";

        try {
            List<Long> list=jdbcTemplate.query(sql, new RowMapper<Long>() {
                @Override
                public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                    Long id=resultSet.getLong(1);
                    return id;
                }
            });
            return new Msg(1,"SUCCESS",list.get(0));


        }catch (Exception e){
            return new Msg(0,"ERROR",null);
        }

    }

    /**
     * 数据库中更新数据---密码
     * @param uid
     * @param password
     * @return
     */
    public Msg updataPassword(Integer uid,String password){
        try {
            String sql="update user set password=? where id=?";
            int result=jdbcTemplate.update(sql,password,uid);
            return new Msg(1,"success",null);
        }catch (Exception e){
            return new Msg(0,"重置失败",null);
        }

    }
}
