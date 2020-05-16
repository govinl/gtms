package com.xyafu.gtms.service;

import com.xyafu.gtms.entity.Power;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘高伟
 * @Classname PowerService
 * @Description TODO
 * @Date 2019/11/27 17:54
 */
public interface PowerService {
    Power selectByPowerAndFunlevel(Power power);
    ArrayList selectByLevel(int funlevel);
    List selectAllPower();
    boolean updatapower(Power power);
}
