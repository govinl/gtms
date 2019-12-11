package com.xyafu.gtms.serviceimpl;

import com.xyafu.gtms.dao.StudentMapper;
import com.xyafu.gtms.entity.Student;
import com.xyafu.gtms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author 刘高伟
 * @Classname StudentServiceImpl
 * @Description 学生表的操作
 * @Date 2019/9/12 22:11
 */
@Service
@Repository
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /*插入学生信息测试类*/
    @Override
    public boolean insertStudent(Student student){
        boolean flag=false;
        student.setStuId("123");
        student.setStuPwd("123");
        student.setStuName("da");
        student.setDept("123");
        student.setZhuanye("1");
        student.setStuPhone("123");
        studentMapper.insert(student);
        flag=true;
        return flag;

    }

    /*学生登录信息判断*/
    @Override
    public boolean login(String stuId,String stuPwd) {
        if(studentMapper.selectByPrimaryKey(stuId)== null){
            return false;
        }else {
            if (studentMapper.selectByPrimaryKey(stuId).getStuPwd().equals(stuPwd)){
                return true;
            }else {
                return false;
            }
        }
    }

    /*根据主键查找学生信息*/
    @Override
    public Student selectByPrimaryKey(String stuId) {
        Student student=studentMapper.selectByPrimaryKey(stuId);
        return student;
    }
    /**
     * 修改学生密码
     */
    @Override
    public boolean changepwd(String userId, String oldpwd, String newpwd) {
        Student student=studentMapper.selectByPrimaryKey(userId);
        if (oldpwd.equals(student.getStuPwd())){
            student.setStuPwd(newpwd);
            studentMapper.updateByPrimaryKey(student);
            return true;
        }else {
            return false;
        }
    }
}
