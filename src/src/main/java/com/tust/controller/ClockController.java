package com.tust.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.comment.QueryPageParam;
import com.tust.comment.Result;
import com.tust.entity.Clock;
import com.tust.service.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 打卡 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/clock")
public class ClockController {

    @Autowired
    private ClockService clockService;

    /**
     * 添加打卡信息
     * @param Id
     * @param name
     * @param location
     * @param health
     * @param temperature
     * @param clockDate
     * @param danger
     * @param contact
     * @param comments
     * @return
     */
    @GetMapping("/addClock")
    public Result addClock(@RequestParam("Id") Integer Id
            , @RequestParam("name") String name
            , @RequestParam("location") String location
            , @RequestParam("health") String health
            , @RequestParam("temperature") String temperature
            , @RequestParam("clockDate") String clockDate
            , @RequestParam("danger") String danger
            , @RequestParam("contact") String contact
            , @RequestParam("comments") String comments){
        Clock clock = new Clock();
        clock.setId(Id);
        clock.setName(name);
        clock.setLocation(location);
        clock.setHealth(health);
        clock.setTemperature(temperature);
        clock.setClockDate(clockDate);
        clock.setDanger(danger);
        clock.setContact(contact);
        clock.setComments(comments);
        return clockService.save(clock) ?Result.success(clock,1) : Result.fail();//myBatisPlus自带的插入方法save()
    }

    /**
     * 查询所有打卡记录
     * @param query
     * @return
     */
    @PostMapping("/selectAllClock")
    public Result selectAllClock(@RequestBody QueryPageParam query) {
        Page<Clock> page = new Page(query.getPageNum(), query.getPageSize());//接收前端的pageNum数据和pageSize数据来确定分页的数据，如果没有那就按照自定义的分页数据
        IPage result = clockService.page(page);//myBatisPlus自带的分页查询，传入分页信息返回数据
        return result != null ? Result.success(result.getRecords(), (int) result.getTotal()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }

    /**
     * 按名字检索打卡记录
     * @param query
     * @return
     */
    @PostMapping("/selectClockByName")
    public Result selectClockByName(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String) param.get("name");//从前端接收的json数据 qurey里封装了一个map，从map里取出名字
        Page<Clock> page = new Page(query.getPageSize(), query.getPageNum());
        LambdaQueryWrapper<Clock> lambdaQueryWrapper = new LambdaQueryWrapper();// 查询条件构造器
        lambdaQueryWrapper.like(Clock::getName,name);//接收的数据与数据库中的名字进行模糊查询
        IPage result = clockService.page(page, lambdaQueryWrapper);//插入分页信息和，模糊查询的数据
        return result!=null ? Result.success(result.getRecords() , (int) result.getTotal()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }
}
