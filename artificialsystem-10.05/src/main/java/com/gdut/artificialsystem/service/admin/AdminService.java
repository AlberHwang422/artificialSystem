package com.gdut.artificialsystem.service.admin;

import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.pojo.User;

public interface AdminService {

    /**
     * 使用excel表
     * 把学生名单添加到数据库
     * @param user
     * @return
     */
    public Msg addBatchStudentByExcel(User user);

    /**
     * 查询未完成表格填写的学生名单
     * @return
     */
    public Msg selectUnfinishedFromStudent(String currentPage,String limitcount);
}
