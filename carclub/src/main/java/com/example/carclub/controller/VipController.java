package com.example.carclub.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.carclub.entity.Vip;
import com.example.carclub.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/vip")
public class VipController {
    @Autowired
    private VipService vipService;

    @PostMapping
    public boolean save(@RequestBody Vip vip){
        return vipService.saveOrUpdate(vip);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        return vipService.removeById(id);
    }

    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return vipService.removeByIds(ids);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Vip> list = vipService.list();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("vipnum", "VIP编号");
        writer.addHeaderAlias("vname", "VIP姓名");
        writer.addHeaderAlias("vsex", "VIP性别");
        writer.addHeaderAlias("vphone", "VIP电话");
        writer.addHeaderAlias("vrank", "VIP等级");
        writer.addHeaderAlias("vmoney", "VIP余额");
        writer.addHeaderAlias("vipid", "VIP身份证号");
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);
        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    @PostMapping("/import")
    public Boolean imp(@RequestParam("file") MultipartFile file) throws Exception {
        if (file ==null){
            System.out.println("错误");
            return false;
        }
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<Vip> vips = CollUtil.newArrayList();
        for (List<Object> row : list) {
            Vip vip = new Vip();
            vip.setVipnum(row.get(0).toString());
            vip.setVname(row.get(1).toString());
            vip.setVsex(row.get(2).toString());
            vip.setVphone(row.get(3).toString());
            vip.setVrank(row.get(4).toString());
            vip.setVmoney(Integer.valueOf(row.get(5).toString()));
            vip.setVipid(row.get(6).toString());
            vips.add(vip);
        }
        vipService.saveBatch(vips);
        return true;
    }

    @GetMapping("/page")
    public IPage<Vip> findpage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String vipnum,
                               @RequestParam(defaultValue = "") String vname,
                               @RequestParam(defaultValue = "") String vipid)
    {
        System.out.println(pageSize);
        IPage<Vip> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Vip> queryWrapper = new QueryWrapper<>();
        if (!"".equals(vipnum))
            queryWrapper.like("vipnum",vipnum);
        if (!"".equals(vname))
            queryWrapper.like("vname",vname);
        if (!"".equals(vipid))
            queryWrapper.like("vipid",vipid);
        return vipService.page(page,queryWrapper);
    }
}
