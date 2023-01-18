package com.example.carclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carclub.common.Result;
import com.example.carclub.entity.Carinfo;
import com.example.carclub.service.CarinfoService;
import com.example.carclub.mapper.CarinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
* @author LENOVO
* @description 针对表【carinfo】的数据库操作Service实现
* @createDate 2022-12-27 21:26:38
*/
@Service
public class CarinfoServiceImpl extends ServiceImpl<CarinfoMapper, Carinfo>
    implements CarinfoService{


    @Resource
    private CarinfoMapper carinfoMapper;

    @Override
    public Result update1(Carinfo carinfo) {
        return carinfoMapper.update1(carinfo);
    }

    @Override
    public Result delete1(int id) {
        return carinfoMapper.delete1(id);
    }

    @Override
    public Result save1(Carinfo carinfo) {
        return carinfoMapper.save1(carinfo);
    }
}




