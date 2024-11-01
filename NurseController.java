package com.hbxy.smartnursery.nurse.controller;

import com.hbxy.smartnursery.nurse.model.Nurse;
import com.hbxy.smartnursery.nurse.model.NurseExample;
import com.hbxy.smartnursery.nurse.service.NurseService;
import com.hbxy.smartnursery.until.IdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

// 添加注解
@Controller
public class NurseController {

    private NurseService nurseService;

    // 通过构�?�器注入服务
    public NurseController(NurseService nurseService){
        this.nurseService=nurseService;
    }

    // 查询数据
    @GetMapping("/nurse")
    @ResponseBody
    public List<Nurse> selectNurse(){
        //1 构�?�查询条�?
        //1.1 创建查询条件�?
        NurseExample example = new NurseExample();
        //1.2 创建条件构�?�器
        NurseExample.Criteria criteria = example.createCriteria();
        //1.3 通过criteria设置条件
        //...

        //2 根据条件查询
        List<Nurse> list = nurseService.selectByExample(example);

        return list;
    }
    // 添加数据
    @PostMapping("/nurse")
    @ResponseBody
    public Nurse insertUser(@RequestBody Nurse nurse){
        // 准备数据
        // 比如：设置主�?

        nurse.setId(IdUtil.nextId9().toString());
        nurse.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        nurse.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        nurseService.insertSelective(nurse);

        return nurse;
    }
    // 修改数据
    @PutMapping("/nurse")
    @ResponseBody
    public Nurse updateUser(@RequestBody Nurse nurse){

        nurse.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        nurseService.updateByPrimaryKeySelective(nurse);
        // 只返回修改的字段数据
        return nurseService.selectByPrimaryKey(nurse.getId());
        // 或�?? 通过主键，从数据库查询完整的数据，然后返回�?�注意获得主键的方法是否正确
        //return nurseService.selectByPrimaryKey(nurse.getNurseId());

    }
    // 删除数据
    @DeleteMapping("/nurse/{id}")
    @ResponseBody
    public String delUser(@PathVariable("id") String nurseId){

        // 要根据数据表主键的类型修改参数类型，�?般位整型或长整型或字符串类型
        nurseService.deleteByPrimaryKey(nurseId);

        // 返回状�?�码
        return "200";
    }
}
