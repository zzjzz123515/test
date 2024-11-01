package com.hbxy.smartnursery.activity.controller;

import com.hbxy.smartnursery.activity.model.Activity;
import com.hbxy.smartnursery.activity.model.ActivityExample;
import com.hbxy.smartnursery.activity.service.ActivityService;
import com.hbxy.smartnursery.until.IdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

// 添加注解
@Controller
public class ActivityController {

    private ActivityService activityService;

    // 通过构�?�器注入服务
    public ActivityController(ActivityService activityService){
        this.activityService=activityService;
    }

    // 查询数据
    @GetMapping("/activity")
    @ResponseBody
    public List<Activity> selectActivity(){
        //1 构�?�查询条�?
        //1.1 创建查询条件�?
        ActivityExample example = new ActivityExample();
        //1.2 创建条件构�?�器
        ActivityExample.Criteria criteria = example.createCriteria();
        //1.3 通过criteria设置条件
        //...

        //2 根据条件查询
        List<Activity> list = activityService.selectByExample(example);

        return list;
    }
    // 添加数据
    @PostMapping("/activity")
    @ResponseBody
    public Activity insertUser(@RequestBody Activity activity){
        // 准备数据
        // 比如：设置主�?
        activity.setId(IdUtil.nextId9().toString());
        activity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        activity.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        activityService.insertSelective(activity);

        return activity;
    }
    // 修改数据
    @PutMapping("/activity")
    @ResponseBody
    public Activity updateUser(@RequestBody Activity activity){

        activity.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        activityService.updateByPrimaryKeySelective(activity);
        // 只返回修改的字段数据
        return activityService.selectByPrimaryKey(activity.getId());
        // 或�?? 通过主键，从数据库查询完整的数据，然后返回�?�注意获得主键的方法是否正确
        //return activityService.selectByPrimaryKey(activity.getActivityId());

    }
    // 删除数据
    @DeleteMapping("/activity/{id}")
    @ResponseBody
    public String delUser(@PathVariable("id") String activityId){

        // 要根据数据表主键的类型修改参数类型，�?般位整型或长整型或字符串类型
        activityService.deleteByPrimaryKey(activityId);

        // 返回状�?�码
        return "200";
    }
}
