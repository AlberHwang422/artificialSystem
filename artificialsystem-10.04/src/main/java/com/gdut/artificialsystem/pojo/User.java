package com.gdut.artificialsystem.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.gdut.artificialsystem.util.StringUtil;

import java.math.BigDecimal;
import java.sql.Date;


@TableName("user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String name;
    private String password;
    private String img;
    @TableField(value = "creatTime")
    private Date creatTime;
    private Integer status;
    private String identityid;
    @TableField(value = "is_submit_characterform")
    private boolean SubmitForm=false;
    private Integer classid;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", img='" + img + '\'' +
                ", creatTime=" + creatTime +
                ", status=" + status +
                ", identityid='" + identityid + '\'' +
                '}';
    }



    public boolean notRightInputUser(){
        if(status==null)
            return true;
        else if(StringUtil.isEmptyStr(password)||StringUtil.isEmptyStr(name)||StringUtil.isEmptyStr(identityid))
            return true;
        else
            return false;
    }
    public Integer getClassid() { return classid; }

    public void setClassid(Integer classid) { this.classid = classid; }

    public void setClassid(String classid){this.classid=Integer.valueOf(classid);}

    public boolean isSubmitForm() {
        return SubmitForm;
    }

    public void setSubmitForm(boolean submitForm) {
        SubmitForm = submitForm;
    }

    public String getIdentityid() {
        return identityid;
    }

    public void setIdentityid(String identityid) {
        this.identityid = identityid;
    }

    public Long getId(){
        return id.longValue();
    }

    public void  setId(String id)   { this.id=Long.valueOf(id); }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
