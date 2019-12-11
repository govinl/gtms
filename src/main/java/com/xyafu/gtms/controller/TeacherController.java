package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Category;
import com.xyafu.gtms.entity.Student;
import com.xyafu.gtms.entity.Teacher;
import com.xyafu.gtms.service.TeacherService;
import com.xyafu.gtms.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 刘高伟
 * @Classname TeacherController
 * @Description teacher对应的操作
 * @Date 2019/9/13 15:00
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;


    /**
     * login
     * 当查找到符合条件的教师信息时，获取该教师权限级别，根据权限转发到相应页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result teacherLogin(@RequestBody Teacher teacher) {
        Result result=new Result();
        if (teacherService.login(teacher.getTeaId(), teacher.getTeaPwd())) {
            teacher = teacherService.selectByPrimaryKey(teacher.getTeaId());
            result.setRes(true);
            result.setMsg("教师登录成功");
            result.setData(teacher);
            int level = Integer.parseInt(teacher.getPlevel());
            switch (level / 1) {
                case 2:
                    result.setUrl("/page/teacher/teacher2_index.html");
                    break;
                case 3:
                    result.setUrl("/page/teacher/teacher3_index.html");
                    break;
                case 4:
                    result.setUrl("/page/teacher/teacher4_index.html");
                    break;
                case 5:
                    result.setUrl("/page/teacher/teacher5_index.html");
                    break;
                default:
            }
            return result;
            /*return  "teacher/teacher_index";*/
        } else {
            result.setRes(false);
            result.setMsg("账户信息不匹配，请重新输入！");
            return result;
            /*return "forward:/teacher/login";*/
        }
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/changepassword",method = RequestMethod.POST)
    public Result changePassword(
            @RequestBody Map<String, Object> map
    ){
        Result result=new Result();
        String oldpwd= (String) map.get("oldpwd");
        String newpwd= (String) map.get("newpwd");
        String confirmpwd= (String) map.get("newpwd");
        String userId=(String) map.get("userId");
        if (teacherService.changepwd(userId,oldpwd,newpwd)){
            result.setRes(true);
            result.setMsg("密码修改成功!");
        }else {
            result.setRes(false);
            result.setMsg("密码修改失败!");
        }
        return  result;
    }


}
