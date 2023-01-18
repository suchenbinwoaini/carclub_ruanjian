package com.example.carclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.common.Result;
import com.example.carclub.entity.Carinfo;
import com.example.carclub.entity.Rescuerecords;
import com.example.carclub.service.CarinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carinfo")
public class CarinfoController {

    @Autowired
    private CarinfoService carinfoService;

    @PostMapping
    public boolean save(@RequestBody Carinfo carinfo){
        return carinfoService.saveOrUpdate(carinfo);
    }

    @DeleteMapping("/{id}")
    public boolean del(@PathVariable int id){
        return carinfoService.removeById(id);
    }

    @GetMapping("/{id}")
    public Result download(@PathVariable int id){
        Carinfo carinfo = carinfoService.getById(id);
        return Result.success(carinfo);
    }

    @GetMapping("/page")
    public IPage<Carinfo> findpage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize)
    {
        System.out.println(pageSize);
        IPage<Carinfo> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Carinfo> queryWrapper = new QueryWrapper<>();
        return carinfoService.page(page,queryWrapper);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Carinfo carinfo){
        return carinfoService.update1(carinfo);
    }

    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable int id){
        return carinfoService.delete1(id);
    }
    @PostMapping("/save1")
    public Result save1(@RequestBody Carinfo carinfo){
        return carinfoService.save1(carinfo);
    }
}
