package com.gdut.artificialsystem.dao.others;

import com.gdut.artificialsystem.util.Msg;
import com.gdut.artificialsystem.util.UserHabitsForm;

public interface UserFormDao {

    /**
     * 数据库中插入信息
     * @param form
     * @return
     */
    public Msg insertHabitsInfo(UserHabitsForm form);
}
