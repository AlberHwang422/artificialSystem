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


    /**
     * 查询未完成表格填写的学生名单
     * @param pageNum 当前页
     * @param limit   一页显示多少数据
     * @return
     */
    public String selectUnfinishedFromStudent(@RequestParam("page") String pageNum,
                                              @RequestParam("limit") String limit);
}
