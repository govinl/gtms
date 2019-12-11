package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Student_subject;
import com.xyafu.gtms.service.StudentSubjectService;
import com.xyafu.gtms.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname SujectController
 * @Description TODO
 * @Date 2019/9/13 22:28
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/subject")
public class SujectController {
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
        int Id=student_subject.getId();
        if(studentSubjectService.deleteOld(Id)){
            result.setRes(true);
        }else {
            result.setRes(false);
        }
        return result;
    }
}
