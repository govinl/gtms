package com.xyafu.gtms.service;

import com.xyafu.gtms.entity.Category;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname CategoryService
 * @Description TODO
 * @Date 2019/9/16 12:30
 */
public interface CategoryService {

    boolean insertCategory(Category category);
    boolean deleteCategory(String cname);
    boolean updatecate(String oldcname,String newcname);
    List selectCategory();
    Category selectByPrimaryKey(int id);
}
