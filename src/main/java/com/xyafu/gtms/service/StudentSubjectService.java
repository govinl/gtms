package com.xyafu.gtms.service;

import com.xyafu.gtms.entity.Student_subject;

import java.util.List;


/**
 * @author 刘高伟
 * @Classname StudentSubjectService
 * @Description TODO
 * @Date 2019/9/13 22:04
 */
public interface StudentSubjectService {
    boolean newSubject(Student_subject student_subject);
    boolean deleteOld(int Id);
    List<Student_subject> selectOwnSubject(String userId);
}
