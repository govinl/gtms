package com.xyafu.gtms.service;

import com.xyafu.gtms.entity.Student;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author 刘高伟
 * @Classname StudentService
 * @Description 对于Student进行操作的方法
 * @Date 2019/9/12 22:10
 */

public interface StudentService {
    boolean insertStudent(Student student);
    boolean login(String stuId,String stuPwd);
    Student selectByPrimaryKey(String stuId);
    boolean changepwd(String userId, String oldpwd, String newpwd);
}
