package com.gdut.artificialsystem.controller.page;

import com.gdut.artificialsystem.dao.user.UserMapper;
import com.gdut.artificialsystem.pojo.User;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;


@Controller
public class PageServletImp implements PageServlet{

    @Autowired
    UserMapper commonMapper;


    //@RequestMapping("/test")
    public String testfun(@RequestParam("file")MultipartFile file) throws IOException
    {
       InputStream is=file.getInputStream();
       XSSFWorkbook workbook=new XSSFWorkbook(is);
       XSSFSheet sheet=workbook.getSheetAt(0);

       int rownum=sheet.getLastRowNum();
       for(int i=0;i<=rownum;i++){
           sheet.getRow(i).getCell(0).setCellType(CellType.STRING);

           String no=sheet.getRow(i).getCell(0).toString();
           String name=sheet.getRow(i).getCell(1).toString();
           String upass=sheet.getRow(i).getCell(2).toString();
           String identity=sheet.getRow(i).getCell(4).toString();


           System.out.println(no+" "+name+" "+upass+" "+identity);
       }

        return null;
    }
    @RequestMapping("/test.html")
    public String test() {

        return "test";
    }
    /**
     * 返回登录主界面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/")
    public String servlet(HttpServletRequest request, HttpServletResponse response){

        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");

        if(user!=null){
            if(user.getStatus()==1)
                return "userindex";
            else
                return "adminindex";
        }
        else
            return "login";
    }

    /**
     * 跳转到login.html
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/login.html")
    public String loginPage(HttpServletRequest request, HttpServletResponse response){

        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @Override
    @RequestMapping("/register.html")
    public String registerPage(HttpServletRequest request, HttpServletResponse response){
        return "register";
    }

    /**
     * 跳转重置密码界面
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping("/reset.html")
    public String resetPage(HttpServletRequest request, HttpServletResponse response){return "reset";}

    /**
     * 跳转到验证页面
     * @return
     */
    @Override
    @RequestMapping("/identitypage.html")
    public String identityPage(HttpServletRequest request, HttpServletResponse response){
        return "identitypage";
    }

    @RequestMapping("/characterform.html")
    @Override
    public String characterPage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session=request.getSession();
        Object obj=session.getAttribute("user");
        if(obj==null){
            return "redirect:/";
        }
        else
            return "characterform";
    }
}
