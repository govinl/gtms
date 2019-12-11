package com.xyafu.gtms.service;

import com.xyafu.gtms.entity.Teacher;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname TeacherService
 * @Description TODO
 * @Date 2019/9/13 15:10
 */
public interface TeacherService {
    boolean login(String teaId,String teaPwd);
    Teacher selectByPrimaryKey(String teaId);
    boolean changepwd(String userId, String oldpwd, String newpwd);
}
