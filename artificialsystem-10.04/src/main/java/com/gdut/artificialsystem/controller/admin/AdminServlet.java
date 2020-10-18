package com.gdut.artificialsystem.controller.admin;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AdminServlet {

    /**
     * 处理从前台获取的学生名单（excel）
     * @param file
     * @return
     */
    public String addBatchStudentByExcel(@RequestParam("listOfStudent") MultipartFile file) throws IOException;
}
