package com.xyafu.gtms.serviceimpl;

import com.xyafu.gtms.dao.Student_subjectMapper;
import com.xyafu.gtms.entity.Student_subject;
import com.xyafu.gtms.service.StudentSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname StudentSubjectServiceImpl
 * @Description 学生自拟题目相关方法
 * @Date 2019/9/13 22:07
 */
@Service
@Repository
public class StudentSubjectServiceImpl implements StudentSubjectService {

    @Autowired
    private Student_subjectMapper student_subjectMapper;


    /**
     * @description 自拟题目
     * @param student_subject
     * @return
     */
    @Override
    public boolean newSubject(Student_subject student_subject) {
        student_subjectMapper.insertSelective(student_subject);
        return true;
    }



    /**
     * @description 根据id查找题目
     * @param userId
     * @return
     */
    @Override
    public List<Student_subject> selectOwnSubject(String userId) {
        List<Student_subject> student_subjects =student_subjectMapper.selectOwnSubject(userId);
        return student_subjects;
    }

    /**
     * 根据学号和题目删除
     */
    @Override
    public boolean deleteOld(int Id) {
        student_subjectMapper.deleteByPrimaryKey(Id);
        return true;
    }


}
