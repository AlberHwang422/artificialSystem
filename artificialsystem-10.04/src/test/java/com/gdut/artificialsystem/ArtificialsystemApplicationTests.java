package com.gdut.artificialsystem;

import com.gdut.artificialsystem.dao.user.UserFormMapper;
import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.pojo.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("com.gdut.artificialsystem.dao")
@SpringBootTest
class ArtificialsystemApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserFormMapper userFormMapper;
    @Test
    void contextLoads() {
//
//        User user=new User();
//        user.setId("10");
//
//        int result=userMapper.insert(user);
//        System.out.println(result+" "+user.toString());

        System.out.println(Long.valueOf("3118004670"));
    }


}
