package com.gdut.artificialsystem;

import com.gdut.artificialsystem.dao.user.UserFormMapper;
import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.util.User;
import com.gdut.artificialsystem.util.UserHabitsForm;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        Map<String,Object> condition=new HashMap<>();
        condition.put("identityid","1397762176@qq.com");
        List<User> result=userMapper.selectByMap(condition);
        result.forEach(System.out::println);

    }


}
