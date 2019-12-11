package com.xyafu.gtms.serviceimpl;

import com.xyafu.gtms.dao.TeadirectionMapper;
import com.xyafu.gtms.entity.Teadirection;
import com.xyafu.gtms.service.TeadirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname TeadirectionServiceImpl
 * @Description TODO
 * @Date 2019/11/29 16:58
 */
@Service
@Repository
public class TeadirectionServiceImpl implements TeadirectionService {
    @Autowired
    private TeadirectionMapper teadirectionMapper;


    @Override
    public List selectByDirection(String direction) {
        List list=teadirectionMapper.selectByDirection(direction);
        return list;
    }

    @Override
    public void insertTeadirection(Teadirection teadirection) {
        teadirectionMapper.insert(teadirection);
    }
}
