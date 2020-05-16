package com.xyafu.gtms.serviceimpl;

import com.xyafu.gtms.dao.PowerMapper;
import com.xyafu.gtms.entity.Power;
import com.xyafu.gtms.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author 刘高伟
 * @Classname PowerServiveImpl
 * @Description TODO
 * @Date 2019/11/27 17:55
 */
@Service
@Repository
public class PowerServiveImpl implements PowerService {
    @Autowired
    private PowerMapper powerMapper;
    /**
     * 查找功能
     * @param powername
     * @return
     */
    @Override
    public Power selectByPrimaryKey(String powername) {
        Power power= powerMapper.selectByPrimaryKey(powername);
        return power;
    }

    @Override
    public ArrayList selectByLevel(int funlevel) {
        ArrayList list=powerMapper.selectByLevel(funlevel);
        return list;
    }
}
