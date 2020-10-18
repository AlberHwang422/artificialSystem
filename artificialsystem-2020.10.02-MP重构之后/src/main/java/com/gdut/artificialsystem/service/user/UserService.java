package com.gdut.artificialsystem.service.user;

import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.User;
import com.gdut.artificialsystem.util.UserHabitsForm;

public interface UserService {

    /**
     * 处理插入生活习惯表的逻辑
     * @param form
     * @return
     */
    public Msg insertUserHabitForm(String id, String question1, String question2,
                                   String question3, String question4, String question5,
                                   String question6);

    /**
     * 设置user属性，已填写habits收集表
     * @param user
     * @return
     */
    public Msg setUserIsSubmitCharacterForm(User user);
}
