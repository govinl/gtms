package com.xyafu.gtms.serviceimpl;

import com.xyafu.gtms.dao.CategoryMapper;
import com.xyafu.gtms.entity.Category;
import com.xyafu.gtms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname CategoryServiceImpl
 * @Description TODO
 * @Date 2019/9/16 12:30
 */
@Service
@Repository
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public boolean insertCategory(Category category) {
        categoryMapper.insert(category);
        return true;
    }

    /**
     * 根据类别的内容删除已有的类别
     * @param cname
     * @return boolean
     */
    @Override
    public boolean deleteCategory(String cname) {
        if (categoryMapper.deleteCategory(cname)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 修改类别
     * @param oldcname
     * @param newcname
     * @return
     */
    @Override
    public boolean updatecate(String oldcname, String newcname) {
        if (categoryMapper.updateCategory(oldcname,newcname)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List selectCategory() {
        List list=categoryMapper.selectCategory();
        return list;
    }

    @Override
    public Category selectByPrimaryKey(int id) {
        Category category=categoryMapper.selectByPrimaryKey(id);
        return category;
    }
}
