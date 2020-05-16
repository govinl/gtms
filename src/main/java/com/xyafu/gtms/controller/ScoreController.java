package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Category;
import com.xyafu.gtms.entity.Team_category;
import com.xyafu.gtms.service.CategoryService;
import com.xyafu.gtms.service.TeamService;
import com.xyafu.gtms.untils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;


/**
 * @author 刘高伟
 * @Classname CategoryController
 * @Description TODO
 * @Date 2019/9/15 19:23
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    /**
     * 查找所有类别
     */
    @RequestMapping(value = "/allCateGory",method = RequestMethod.POST)
    public Result selectCategory(){
        Result result=new Result();
       List<Category> categories= categoryService.selectCategory();
       result.setRes(true);
       result.setData(categories);
        return result;
    }

    /**
     * 新增类别
     */
    @RequestMapping(value = "/newcategory",method = RequestMethod.POST)
    public Result insertCategory(@RequestBody Category category){
        Result result=new Result();
            if (categoryService.insertCategory(category)){
                result.setRes(true);
                result.setMsg("新类别已提交!");
            }else {
                result.setRes(false);
                result.setMsg("类别提交失败，请刷新重试!");
            }
            return result;
    }

    /**
     * 根据内容删除类别
     * @param category
     * @return result
     */
    @RequestMapping(value = "/deletecategory",method = RequestMethod.POST)
    public Result deleteCategory(@RequestBody Category category){
        Result result=new Result();
        String cname=category.getCname();
        if (categoryService.deleteCategory(cname)){
            result.setRes(true);
            result.setMsg("类别已删除！");
        }else {
            result.setRes(true);
            result.setMsg("类别删除失败！");
        }
        return result;
    }

    /**
     * 根据oldcname更新类别
     * @param
     * @return
     */
    @RequestMapping(value = "/updatecategory",method = RequestMethod.POST)
    public Result updateCategory(@RequestBody Map<String, Object> map){
        Result result=new Result();
        String oldname= (String) map.get("oldcname");
        String newcname=(String)map.get("newcname");
        if (categoryService.updatecate(oldname,newcname)){
            result.setRes(true);
            result.setMsg("类别修改成功！");
        }else {
            result.setRes(true);
            result.setMsg("类别修改失败，请尝试清除cookie后重试！");
        }
        return result;
    }

}
