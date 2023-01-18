package com.example.carclub.mapper;

import com.example.carclub.common.Result;
import com.example.carclub.entity.Carinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author LENOVO
* @description 针对表【carinfo】的数据库操作Mapper
* @createDate 2022-12-27 21:26:38
* @Entity com.example.carclub.entity.Carinfo
*/
public interface CarinfoMapper extends BaseMapper<Carinfo> {

    Result update1(Carinfo carinfo);

    Result delete1(int id);

    Result save1(Carinfo carinfo);
}




