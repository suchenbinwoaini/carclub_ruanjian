package com.example.carclub.service;

import com.example.carclub.common.Result;
import com.example.carclub.entity.Carinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author LENOVO
* @description 针对表【carinfo】的数据库操作Service
* @createDate 2022-12-27 21:26:38
*/
public interface CarinfoService extends IService<Carinfo> {

    Result update1(Carinfo carinfo);

    Result delete1(int id);

    Result save1(Carinfo carinfo);
}
