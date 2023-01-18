package com.example.carclub.service;

import com.example.carclub.common.Result;
import com.example.carclub.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carclub.entity.dto.Employeedto;

import java.util.List;

/**
* @author LENOVO
* @description 针对表【employee】的数据库操作Service
* @createDate 2022-11-21 23:26:58
*/
public interface EmployeeService extends IService<Employee> {
    int update(Employee employee);
    int deleteById(Integer Enum);
    Employeedto login(Employeedto employeedto);

    List<Employee> findAll();

    Result save1(Employee employee);
}
