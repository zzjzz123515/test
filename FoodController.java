package com.hbxy.smartnursery.food.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbxy.smartnursery.food.model.Food;
import com.hbxy.smartnursery.food.model.FoodExample;
import com.hbxy.smartnursery.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// 添加注解
@Controller
public class FoodController {

    private FoodService foodService;

    // 通过构�?�器注入服务
    public FoodController(FoodService foodService){
        this.foodService=foodService;
    }

    // 查询数据
    @GetMapping("/food")
    @ResponseBody
    public List<Food> selectFood(){
        //1 构�?�查询条�?
        //1.1 创建查询条件�?
        FoodExample example = new FoodExample();
        //1.2 创建条件构�?�器
        FoodExample.Criteria criteria = example.createCriteria();
        //1.3 通过criteria设置条件
        //...

        //2 根据条件查询
        List<Food> list = foodService.selectByExample(example);

        return list;
    }
    // 添加数据
    @PostMapping("/food")
    @ResponseBody
    public Food insertUser(@RequestBody Food food){
        // 准备数据
        // 比如：设置主�?

        foodService.insertSelective(food);

        return food;
    }
    // 修改数据
    @PutMapping("/food")
    @ResponseBody
    public Food updateUser(@RequestBody Food food){

        food.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        foodService.updateByPrimaryKeySelective(food);
        // 只返回修改的字段数据
        return foodService.selectByPrimaryKey(food.getId());
        // 或�?? 通过主键，从数据库查询完整的数据，然后返回�?�注意获得主键的方法是否正确
        //return foodService.selectByPrimaryKey(food.getFoodId());

    }
    // 删除数据
    @DeleteMapping("/food/{id}")
    @ResponseBody
    public String delUser(@PathVariable("id") String foodId){

        // 要根据数据表主键的类型修改参数类型，�?般位整型或长整型或字符串类型
        foodService.deleteByPrimaryKey(foodId);

        // 返回状�?�码
        return "200";
    }
}






