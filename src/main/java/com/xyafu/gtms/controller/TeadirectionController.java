package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Teadirection;
import com.xyafu.gtms.service.TeadirectionService;
import com.xyafu.gtms.untils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname Teadirection
 * @Description TODO
 * @Date 2019/11/29 16:53
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/teadirection")
public class TeadirectionController {
    @Autowired
    private TeadirectionService teadirectionService;

    @RequestMapping(value = "/selectbydirection",method = RequestMethod.POST)
    public Result selectByDirection(@RequestBody Teadirection teadirection){
        Result result=new Result();
        String direction=teadirection.getDirection();
        List list=teadirectionService.selectByDirection(direction);
        result.setRes(true);
        result.setData(list);
        return  result;
    }
}
