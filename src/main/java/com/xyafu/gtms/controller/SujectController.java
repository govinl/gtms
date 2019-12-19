package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Student_subject;
import com.xyafu.gtms.service.StudentSubjectService;
import com.xyafu.gtms.untils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 刘高伟
 * @Classname SujectController
 * @Description TODO
 * @Date 2019/9/13 22:28
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/subject")
public class SujectController  {
    @Autowired
    private StudentSubjectService studentSubjectService;

    /**
     * 自拟题目
     */

    //@ResponseBody/*用于将返回的值直接写入HTTP response body中*/
    /**
     * 在使用@RequestMapping后，返回值通常解析为跳转路径，加上@esponsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中
     */
    @RequestMapping(value = "/newsubject",method = RequestMethod.POST)
    public Result newSubject(@RequestBody  Student_subject student_subject) {
        Result result = new Result();
        if(studentSubjectService.newSubject(student_subject)){
            result.setRes(true);
            result.setMsg("题目已成功提交！");
            return result;
        } else {
            result.setRes(false);
            result.setMsg("功能未开启！");
            return result;
        }
    }

    /**
     * 根据学生id查找该学生自拟的题目
     */
    @RequestMapping(value = "/selectOwnSubject",method = RequestMethod.POST)
    public Result SelectOwnSubject(@RequestBody  Student_subject student_subject){
        Result result=new Result();
        String userId=student_subject.getStuId();
        List<Student_subject> student_subjects=studentSubjectService.selectOwnSubject(userId);
        if (student_subjects.size()==0) {
            result.setRes(false);
        }else {
            result.setRes(true);
            result.setData(student_subjects);
        }
        return result;
    }

    /**
     * 根据题目id删除题目
     */
    @RequestMapping(value = "/deleteOld",method = RequestMethod.POST)
    public Result DeleteOld(@RequestBody  Student_subject student_subject){
        Result result=new Result();
        Object Id=student_subject.getId();
        if(studentSubjectService.deleteOld(Id)){
            result.setRes(true);
        }else {
            result.setRes(false);
        }
        return result;
    }

    /**
     * 根据审核状态查找题目
     * @param yN
     * @return
     */
    @RequestMapping(value = "/selectSubjectByYn",method = RequestMethod.POST)
    public Result selectSubjectByYn(@Param("yN") int yN,@Param("page") int page,@Param("limit") int limit){
        Result result=new Result();
        int pages=(page-1)*10;
        List<Student_subject> student_subjects=studentSubjectService.selectSubjectByYn(yN,pages,limit);
        result.setRes(true);
        //查询结果集的大小，用于实现前台分页（limit设置为99999只是为了保证在大批量结果集中，前台数据总量显示正确）
        List<Student_subject> student_subjects1=studentSubjectService.selectSubjectByYn(yN,0,99999);
        result.setMsg(String.valueOf(student_subjects1.size()));
        result.setData(student_subjects);
        return result;
    }

    /**
     * 题目审核
     * @param map
     * @return
     */
    @RequestMapping(value = "/review",method = RequestMethod.POST)
    public Result review(@RequestBody Map<String,Object> map){
        Result result=new Result();
        ArrayList idlist=(ArrayList) map.get("id");
        Object[] idArray=idlist.toArray();
        String type=(String)map.get("type");
        for (int i=0;i<idlist.size();i++) {
            int id=(int)idArray[i];
            Student_subject student_subject = new Student_subject();
            student_subject.setId(id);
            //根据type判断是否通过审核
            if (type.equals("pass")) {
                byte newyn = 1;
                student_subject.setyN(newyn);
            } else if (type.equals("fail")) {
                byte newyn = 2;
                student_subject.setyN(newyn);
            } else {
                result.setMsg("接口状态异常，请稍后再试！");
            }
            //提交操作
            if (studentSubjectService.updateByPrimaryKeySelective(student_subject)) {
                result.setRes(true);
                result.setMsg("操作已成功提交！");
            } else {
                result.setMsg("接口状态异常，请稍后再试！");
            }
        }
        return result;
    }
}
