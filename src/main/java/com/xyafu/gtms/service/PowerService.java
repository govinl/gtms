package com.xyafu.gtms.service;

import com.xyafu.gtms.entity.Power;

import java.util.ArrayList;

/**
 * @author 刘高伟
 * @Classname PowerService
 * @Description TODO
 * @Date 2019/11/27 17:54
 */
public interface PowerService {
    Power selectByPrimaryKey(String powername);
    ArrayList selectByLevel(int funlevel);
}
