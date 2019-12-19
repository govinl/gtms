package com.xyafu.gtms.serviceimpl;

import com.xyafu.gtms.dao.Teacher_topicMapper;
import com.xyafu.gtms.entity.Teacher_topic;
import com.xyafu.gtms.service.TeacherTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname TeacherTopicServiceImpl
 * @Description TODO
 * @Date 2019/9/16 17:27
 */
@Service
@Repository
public class TeacherTopicServiceImpl implements TeacherTopicService {
    @Autowired
    private Teacher_topicMapper teacher_topicMapper;

    @Override
    public boolean updateTopic(Teacher_topic teacher_topic) {
        teacher_topicMapper.updateByPrimaryKeySelective(teacher_topic);
        return true;
    }

    @Override
    public boolean deleteTopic(int id) {
        teacher_topicMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean insertTopic(Teacher_topic teacher_topic) {
        teacher_topicMapper.insertSelective(teacher_topic);
        return true;
    }

    @Override
    public List selectMyTopic(String teaId) {
        List list=teacher_topicMapper.selectMyTopic(teaId);
        return list;
    }

    @Override
    public List<Teacher_topic> selectTopicByYn(int yN, int pages, int limit) {
        List<Teacher_topic> teacher_topics=teacher_topicMapper.selectTopicByYn(yN,pages,limit);
        return teacher_topics;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Teacher_topic teacher_topic) {
        teacher_topicMapper.updateByPrimaryKeySelective(teacher_topic);
        return true;
    }
}
