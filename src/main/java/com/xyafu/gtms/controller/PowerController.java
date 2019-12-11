package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Power;
import com.xyafu.gtms.service.PowerService;
import com.xyafu.gtms.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author 刘高伟
 * @Classname PowerController
 * @Description TODO
 * @Date 2019/11/27 17:44
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/power")
public class PowerController {
    @Autowired
    private PowerService powerService;

    /**
     * 判断功能是否开启
     * 新增功能后需要在switch..case..区间内增加相应的url
     * @param power
     * @return result
     */
    @RequestMapping(value = "/checkOnOff",method = RequestMethod.POST)
    public Result checkOnOff(@RequestBody Power power){
        Result result=new Result();
        String powername=power.getPower();
        Power powernow=powerService.selectByPrimaryKey(powername);
        if (powernow.getOnoff()){
            result.setRes(true);
            switch (powername){
                case "自拟题目":
                    result.setUrl("/page/student/student_zinitimu.html");
                    break;
                case "测试":
                    result.setUrl("/page/login.html");
                    break;
                case "拟定类别":
                    result.setUrl("/page/teacher/funpage/teacher4_leibie.html");
                    break;
                case "拟定题目":
                    result.setUrl("/page/teacher/funpage/teacher2_topic.html");
                    break;
                /**
                 * 在此增加新的case
                 */
            }
        }else {
            result.setRes(false);
            result.setMsg("功能未开启，请等候功能开启再访问！");
        }
        return result;
    }

    /**
     * 根据权限查找功能
     */
    @RequestMapping(value = "/ownpower",method = RequestMethod.POST)
    public Result selectByLevel(@RequestBody Power power){
        Result result=new Result();
        int funlevel=power.getFunlevel();
        ArrayList list=powerService.selectByLevel(funlevel);
        result.setRes(true);
        result.setData(list);
        return result;
    }

}
