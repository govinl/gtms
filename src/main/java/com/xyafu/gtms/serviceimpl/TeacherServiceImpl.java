package com.xyafu.gtms.serviceimpl;

import com.xyafu.gtms.dao.TeacherMapper;
import com.xyafu.gtms.entity.Student;
import com.xyafu.gtms.entity.Teacher;
import com.xyafu.gtms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname TeacherServiceImpl
 * @Description TODO
 * @Date 2019/9/13 15:12
 */
@Service
@Repository
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    /*教师登录信息判断*/
    @Override
    public boolean login(String teaId,String teaPwd) {
        if(teacherMapper.selectByPrimaryKey(teaId)!=null){
            if(teacherMapper.selectByPrimaryKey(teaId).getTeaPwd().equals(teaPwd)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 获取教师权限级别，判断跳转页面
     */
    @Override
    public Teacher selectByPrimaryKey(String teaId) {
        Teacher teacher=teacherMapper.selectByPrimaryKey(teaId);
        return teacher;
    }

    /**
     * 修改密码
     */
    @Override
    public boolean changepwd(String userId, String oldpwd, String newpwd) {
        Teacher teacher=teacherMapper.selectByPrimaryKey(userId);
        if (oldpwd.equals(teacher.getTeaPwd())){
            teacher.setTeaPwd(newpwd);
            teacherMapper.updateByPrimaryKey(teacher);
            return true;
        }else {
            return false;
        }
    }

}
