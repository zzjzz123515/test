package com.hbxy.smartnursery.children.controller;

import com.hbxy.smartnursery.children.model.Children;
import com.hbxy.smartnursery.children.model.ChildrenExample;
import com.hbxy.smartnursery.children.service.ChildrenService;
import com.hbxy.smartnursery.until.IdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

// 添加注解
@Controller
public class ChildrenController {

    private ChildrenService childrenService;

    // 通过构�?�器注入服务
    public ChildrenController(ChildrenService childrenService){
        this.childrenService=childrenService;
    }

    // 查询数据
    @GetMapping("/children")
    @ResponseBody
    public List<Children> selectChildren(){
        //1 构�?�查询条�?
        //1.1 创建查询条件�?
        ChildrenExample example = new ChildrenExample();
        //1.2 创建条件构�?�器
        ChildrenExample.Criteria criteria = example.createCriteria();
        //1.3 通过criteria设置条件
        //...

        //2 根据条件查询
        List<Children> list = childrenService.selectByExample(example);

        return list;
    }
    // 添加数据
    @PostMapping("/children")
    @ResponseBody
    public Children insertUser(@RequestBody Children children){
        // 准备数据
        // 比如：设置主�?\
        children.setId(IdUtil.nextId9().toString());
        children.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        children.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        childrenService.insertSelective(children);

        return children;
    }
    // 修改数据
    @PutMapping("/children")
    @ResponseBody
    public Children updateUser(@RequestBody Children children){

        children.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        childrenService.updateByPrimaryKeySelective(children);
        // 只返回修改的字段数据
        return childrenService.selectByPrimaryKey(children.getId());
        // 或�?? 通过主键，从数据库查询完整的数据，然后返回�?�注意获得主键的方法是否正确
        //return childrenService.selectByPrimaryKey(children.getChildrenId());

    }
    // 删除数据
    @DeleteMapping("/children/{id}")
    @ResponseBody
    public String delUser(@PathVariable("id") String childrenId){

        // 要根据数据表主键的类型修改参数类型，�?般位整型或长整型或字符串类型
        childrenService.deleteByPrimaryKey(childrenId);

        // 返回状�?�码
        return "200";
    }
}
