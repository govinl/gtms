package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Student;
import com.xyafu.gtms.service.StudentService;
import com.xyafu.gtms.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 刘高伟
 * @Classname UserController
 * @Description TODO
 * @Date 2019/9/12 0:08
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*插入学生信息的test类*/
    @RequestMapping("/insertstudent")
    public boolean insertStudent(Student student){
        return studentService.insertStudent(student);
    }
    /*测试成功*/

    /**
     * login
     * 学生登录方法
     * 当未查找到符合条件的学生信息时，自动重定向到教师登录
     * @return
     */

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result studentLogin(@RequestBody Student student){
        Result result=new Result();
        if(studentService.login(student.getStuId(),student.getStuPwd())){
            student=studentService.selectByPrimaryKey(student.getStuId());
            result.setRes(true);
            result.setMsg("学生登录成功");
            result.setData(student);
            result.setUrl("/page/student/student_index.html");
            return result;
            /*return  "student.js/student_index";*/
        }else {
            result.setRes(false);
            return result;
            /*return "forward:/teacher/login";*/
        }
    }

    /**
     * 注销
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public Result studentLogout(Model model){
        Result result=new Result();
        model.addAttribute("student",new Student());
        result.setUrl("/page/login.html");
        return result;
     }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/changepassword",method = RequestMethod.POST)
    public Result changePassword(
           /* @RequestParam("oldpwd") String oldpwd,
            @RequestParam("newpwd") String newpwd,
            @RequestParam("confirmpwd") String confirmpwd*/
            @RequestBody Map<String, Object> map
    ){
        Result result=new Result();
        String oldpwd= (String) map.get("oldpwd");
        String newpwd= (String) map.get("newpwd");
        String confirmpwd= (String) map.get("newpwd");
        String userId=(String) map.get("userId");
        if (studentService.changepwd(userId,oldpwd,newpwd)){
            result.setRes(true);
            result.setMsg("密码修改成功!");
        }else {
            result.setRes(false);
            result.setMsg("密码修改失败!");
        }
        return  result;
    }


}
