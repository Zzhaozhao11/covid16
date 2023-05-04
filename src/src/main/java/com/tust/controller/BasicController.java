package com.tust.controller;


import com.tust.comment.Result;
import com.tust.entity.Basic;
import com.tust.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 基本信息 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-11-11
 */
@RestController
@RequestMapping("/basic")
public class BasicController {

    @Autowired
    private BasicService basicService;
    /**
     * 新建病人基本信息
     * @param name
     * @param personId
     * @param age
     * @param gender
     * @param houseAddress
     * @param phone
     * @return
     */
    @GetMapping("/addBasic")
    public Result addBase(@RequestParam("name") String name
            , @RequestParam("personId") String personId
            , @RequestParam("age") Integer age
            , @RequestParam("gender") String gender
            , @RequestParam("houseAddress") String houseAddress
            , @RequestParam("phone") String phone){
        Basic basic = new Basic();
        basic.setName(name);
        basic.setPersonId(personId);
        basic.setAge(age);
        basic.setGender(gender);
        basic.setHouseAddress(houseAddress);
        basic.setPhone(phone);
        return basicService.save(basic) ?Result.success(basic,1) : Result.fail();
    }


    /**
     * 查询基本信息
     * @param id
     * @return
     */
    @GetMapping("/getBasic")
    public Result getBase(@RequestParam("id") Integer id){
        Basic base = basicService.getBase(id);
        return base!=null ? Result.success(base, 1) : Result.fail();
    }
}
