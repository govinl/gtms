package com.xyafu.gtms.service;

import com.xyafu.gtms.entity.Student_subject;
import com.xyafu.gtms.entity.Teacher_topic;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname TeacherTopicService
 * @Description TODO
 * @Date 2019/9/16 17:26
 */
public interface TeacherTopicService {
    boolean updateTopic(Teacher_topic teacher_topic);
    boolean deleteTopic(int id);
    boolean insertTopic(Teacher_topic teacher_topic);
    List selectMyTopic(String teaId);
    List<Teacher_topic> selectTopicByYn(int yN, int pages, int limit);
    boolean updateByPrimaryKeySelective(Teacher_topic teacher_topic);
}
