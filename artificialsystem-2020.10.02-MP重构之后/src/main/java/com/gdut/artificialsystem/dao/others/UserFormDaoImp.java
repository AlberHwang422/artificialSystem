package com.gdut.artificialsystem.dao.others;

import com.gdut.artificialsystem.dao.others.UserFormDao;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.UserHabitsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserFormDaoImp implements UserFormDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Msg insertHabitsInfo(UserHabitsForm form) {
        try{
            String sql="insert into user_habits (userid,question1,question2,question3,question4,question5,question6) values(?,?,?,?,?,?,?)";
            int result=jdbcTemplate.update(sql,form.getId(),form.getAnswer1(),form.getAnswer2(),form.getAnswer3(),form.getAnswer4(),form.getAnswer5(),form.getAnswer6());
            if(result>0){
                return new Msg(1,"SUCCESSS",null);
            }
        }catch (Exception e){
            return new Msg(0,"ERROR",null);
        }
        return new Msg(0,"ERROR",null);
    }
}
