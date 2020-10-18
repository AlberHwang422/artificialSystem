package com.gdut.artificialsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@MapperScan("com.gdut.artificialsystem.dao")
@SpringBootApplication
public class ArtificialsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtificialsystemApplication.class, args);
    }
}
