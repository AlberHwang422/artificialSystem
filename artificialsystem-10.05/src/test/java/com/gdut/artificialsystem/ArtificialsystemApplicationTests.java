package com.gdut.artificialsystem;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gdut.artificialsystem.dao.user.UserFormMapper;
import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.pojo.User;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.UserPageUtil;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MapperScan("com.gdut.artificialsystem.dao")
@SpringBootTest
class ArtificialsystemApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserFormMapper userFormMapper;
    @Test
    void contextLoads() {
//        UserPageUtil userPageUtil=new UserPageUtil();
//        User user=new User();
//        user.setId("1111");
//        userPageUtil.addResult(user);
//
//        User user2=new User();
//        user.setId("2222");
//        user.setPassword("sadfasdf");
//        userPageUtil.addResult(user);
//
//        String json=JSON.toJSONString(userPageUtil);
//        System.out.println(json);
//        Map<String,Object> conditon=new HashMap<>();
//
//        List<User> resultlist=userMapper.selectByMap(conditon);
//        System.out.println(resultlist);
        EntityWrapper<User> condition=new EntityWrapper<>();
        condition.eq("is_submit_characterform","0").gt("id",2);

        List<User> resultlist=userMapper.selectList(condition);
        System.out.println(resultlist);
    }


}
