package com.gdut.artificialsystem.controller.admin;

import com.alibaba.fastjson.JSON;
import com.gdut.artificialsystem.service.admin.AdminService;
import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.pojo.User;
import com.gdut.artificialsystem.util.UserPageUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminServletImp implements AdminServlet{

    @Autowired
    AdminService adminServiceImp;


    /**
     * 处理从前台获取的学生名单（excel）
     * 批量插入数据库
     * @param file
     * @return
     */
    @RequestMapping("/adgadfadgag")
    @Override
    public String addBatchStudentByExcel(@RequestParam("file") MultipartFile file) throws IOException {

        //StringBuffer error_tips =new StringBuffer("Wrong number is: ");
        List<String> error_tips=new ArrayList<>();

        //1.获取输入流
        InputStream is=file.getInputStream();
        //2.获取excel工具类
        XSSFWorkbook workbook=new XSSFWorkbook(is);
        //2.1获取excel表的页
        XSSFSheet sheet=workbook.getSheetAt(0);
        //2.2获取该页有多少行数据
        int rownum=sheet.getLastRowNum();

        //3.把数据进行解读和封装
        for(int i=1;i<=rownum;i++){
            User user=new User();
            //3.1把表中的数字转为字符串类型
            sheet.getRow(i).getCell(4).setCellType(CellType.STRING);

            //3.2读取表中数据
            user.setName(sheet.getRow(i).getCell(0).toString());
            user.setPassword(sheet.getRow(i).getCell(1).toString());
            user.setIdentityid(sheet.getRow(i).getCell(3).toString());
//          user.setDate(sheet.getRow(i).getCell(3).toString());
            user.setClassid(sheet.getRow(i).getCell(4).toString());
            user.setStatus(1);

            //3.3调用对性的service方法
            Msg msg=adminServiceImp.addBatchStudentByExcel(user);

            //3.4检查
            //3.4.1  把错误信息，放入wrong_number字符缓冲区。待稍后输出
            if(msg.getStatusCode()==0)
            {
//              error_tips.append(user.getId()+",");
                error_tips.add(user.getId().toString());
            }
        }

        return "uploadtest";
    }


    /**
     * 查询未完成表格填写的学生名单
     * @param pageNum 当前页
     * @param limit   一页显示多少数据
     * @return
     */
    @RequestMapping("/select-unfinished-student")
    @ResponseBody
    public String selectUnfinishedFromStudent(@RequestParam("page") String pageNum,
                                              @RequestParam("limit") String limit){

        //1.调用service方法
        Msg msg=adminServiceImp.selectUnfinishedFromStudent(pageNum,limit);

        //2.检查结果
        //2.1 fail
        if(msg.getStatusCode()==0){
            return null;
        }
        //2.2 success
        UserPageUtil userPageUtil= (UserPageUtil) msg.getObject();
        String json=JSON.toJSONString(userPageUtil);

        return json;
    }
}
