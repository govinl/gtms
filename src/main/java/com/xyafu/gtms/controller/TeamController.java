package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Student_subject;
import com.xyafu.gtms.entity.Teacher_topic;
import com.xyafu.gtms.entity.Teadirection;
import com.xyafu.gtms.service.TeacherTopicService;
import com.xyafu.gtms.service.TeadirectionService;
import com.xyafu.gtms.untils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.ArrayList;
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

    /**
     * 根据审核状态查找题目
     * @param yN
     * @return
     */
    @RequestMapping(value = "/selectTopicByYn",method = RequestMethod.POST)
    public Result selectSubjectByYn(@Param("yN") int yN, @Param("page") int page, @Param("limit") int limit){
        Result result=new Result();
        int pages=(page-1)*10;
        List<Teacher_topic> teacher_topics=teacherTopicService.selectTopicByYn(yN,pages,limit);
        result.setRes(true);
        //查询结果集的大小，用于实现前台分页（limit设置为99999只是为了保证在大批量结果集中，前台数据总量显示正确）
        List<Teacher_topic> teacher_topics1=teacherTopicService.selectTopicByYn(yN,0,99999);
        result.setMsg(String.valueOf(teacher_topics1.size()));
        result.setData(teacher_topics);
        return result;
    }

    /**
     * 题目审核
     * @param map
     * @return
     */
    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public  Result review(@RequestBody Map<String,Object> map){
        Result result=new Result();
        ArrayList idlist=(ArrayList) map.get("id");
        Object[] idArray=idlist.toArray();
        String type=(String)map.get("type");
        for (int i=0;i<idlist.size();i++) {
            int id=(int)idArray[i];
            Teacher_topic teacher_topic=new Teacher_topic();
            teacher_topic.setId(id);
            //根据type判断是否通过审核
            if (type.equals("pass")) {
                byte newyn = 1;
                teacher_topic.setyN(newyn);
            } else if (type.equals("fail")) {
                byte newyn = 2;
                teacher_topic.setyN(newyn);
            } else {
                result.setMsg("接口状态异常，请稍后再试！");
            }
            //提交操作
            if (teacherTopicService.updateByPrimaryKeySelective(teacher_topic)) {
                result.setRes(true);
                result.setMsg("操作已成功提交！");
            } else {
                result.setMsg("接口状态异常，请稍后再试！");
            }
        }
        return result;
    }

    /**
     * 根据条件查找需要导出的数据
     * @param map
     * @return
     */
    @RequestMapping(value = "/exportTopic",method = RequestMethod.POST)
    public  Result export(@RequestBody Map<String,Object> map){
        Result result=new Result();
        String teaId=(String) map.get("teaId");
        String teaName=(String) map.get("teaName");
        String cname=(String) map.get("cate");
        //判断参数yn为“on”，则yn=1，否则为0
        int yn=((boolean)map.get("yn")==true?1:0);
        List list=teacherTopicService.selectExportData(teaId,teaName,cname,yn);
        result.setRes(true);
        result.setData(list);
        return result;
    }

}
