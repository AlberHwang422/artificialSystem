package com.gdut.artificialsystem.pojo;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName(value = "user_habits")
public class UserHabitsForm {

    @TableId(value = "userid",type = IdType.INPUT)
    private Long id;
    @TableField("question1")
    private Integer answer1;
    @TableField("question2")
    private Integer answer2;
    @TableField("question3")
    private Integer answer3;
    @TableField("question4")
    private Integer answer4;
    @TableField("question5")
    private Integer answer5;
    @TableField("question6")
    private Integer answer6;

    public Integer getAnswer1() {
        return answer1;
    }

    public void setId(Long id){this.id=id;}

    public void setId(String id) {this.id=Long.valueOf(id);}

    public Long getId(){return id;}

    public void setAnswer1(Integer answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer1(String answer1){this.answer1= Integer.valueOf(answer1); }

    public Integer getAnswer2() {
        return answer2;
    }

    public void setAnswer2(Integer answer2) {
        this.answer2 = answer2;
    }

    public void setAnswer2(String answer2){this.answer2= Integer.valueOf(answer2); }

    public Integer getAnswer3() {
        return answer3;
    }

    public void setAnswer3(Integer answer3) {
        this.answer3 = answer3;
    }

    public void setAnswer3(String answer3){this.answer3= Integer.valueOf(answer3); }

    public Integer getAnswer4() {
        return answer4;
    }

    public void setAnswer4(Integer answer4) {
        this.answer4 = answer4;
    }

    public void setAnswer4(String answer4){this.answer4= Integer.valueOf(answer4); }

    public Integer getAnswer5() {
        return answer5;
    }

    public void setAnswer5(Integer answer5) {
        this.answer5 = answer5;
    }

    public void setAnswer5(String answer5){this.answer5= Integer.valueOf(answer5); }

    public Integer getAnswer6() {
        return answer6;
    }

    public void setAnswer6(Integer answer6) {
        this.answer6 = answer6;
    }

    public void setAnswer6(String answer6){this.answer6= Integer.valueOf(answer6); }

    @Override
    public String toString() {
        return "UserHabitsForm{" +
                "id=" + id +
                ", answer1=" + answer1 +
                ", answer2=" + answer2 +
                ", answer3=" + answer3 +
                ", answer4=" + answer4 +
                ", answer5=" + answer5 +
                ", answer6=" + answer6 +
                '}';
    }
}
