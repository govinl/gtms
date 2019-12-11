package com.xyafu.gtms.service;

import com.xyafu.gtms.entity.Teadirection;

import java.util.List;

/**
 * @author 刘高伟
 * @Classname TeadirectionService
 * @Description TODO
 * @Date 2019/11/29 16:56
 */
public interface TeadirectionService {
    List selectByDirection(String direction);
    void insertTeadirection(Teadirection teadirection);
}
