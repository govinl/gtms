package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Teacher_topic;
import com.xyafu.gtms.entity.Teadirection;
import com.xyafu.gtms.service.TeacherTopicService;
import com.xyafu.gtms.service.TeadirectionService;
import com.xyafu.gtms.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 刘高伟
 * @Classname TopicController
 * @Description TODO
 * @Date 2019/9/16 17:25
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TeacherTopicService teacherTopicService;
    @Autowired
    private TeadirectionService teadirectionService;

    /**
     * 教师提交自拟的选题
     */
    @RequestMapping(value = "/insertTopic",method = RequestMethod.POST)
    public Result insertTopic(@RequestBody Teacher_topic teacher_topic){
        Result result=new Result();
        if(teacherTopicService.insertTopic(teacher_topic)){
            Teadirection teadirection=new Teadirection();
            teadirection.setTeaId(teacher_topic.getTeaId());
            teadirection.setTeaName(teacher_topic.getTeaName());
            teadirection.setDirection(teacher_topic.getCname());
            teadirectionService.insertTeadirection(teadirection);
            result.setRes(true);
            result.setMsg("题目插入成功！");
        }else {
            result.setRes(false);
            result.setMsg("题目插入失败！");
        }
        return result;
    }

    /**
     * 教师查看自拟的选题
     */
    @RequestMapping(value = "/selectowntopic",method = RequestMethod.POST)
    public Result selectMyTopic(@RequestBody Teacher_topic teacher_topic){
        Result result=new Result();
        String teaId=teacher_topic.getTeaId();
        List list=teacherTopicService.selectMyTopic(teaId);
        result.setRes(true);
        result.setData(list);
        return result;
    }
    /**
     * 删除题目
     */
    @RequestMapping(value = "/deletetopic",method = RequestMethod.POST)
    public Result deleteTopic(@RequestBody Teacher_topic teacher_topic){
        Result result=new Result();
        int id=teacher_topic.getId();
        if (teacherTopicService.deleteTopic(id)){
            result.setRes(true);
            result.setMsg("题目已删除！");
        }else {
            result.setRes(false);
            result.setMsg("删除失败，请稍后再试！");
        }
        return result;
    }
    /**
     * 修改题目
     */
    @RequestMapping(value = "/changetopic",method = RequestMethod.POST)
    public Result changeTopic(@RequestBody Teacher_topic teacher_topic){
        Result result=new Result();
        if (teacherTopicService.updateTopic(teacher_topic)){
            result.setRes(true);
            result.setMsg("题目修改成功！");
        }else {
            result.setRes(false);
            result.setMsg("题目修改失败，请稍后再试！");
        }
        return result;
    }
}
