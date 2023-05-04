package com.tust.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.comment.QueryPageParam;
import com.tust.comment.Result;
import com.tust.entity.User;
import com.tust.service.UserService;
import com.tust.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册信息
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.save(user) ? Result.success(null,1) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }

    /**
     * 登录实现
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List list = userService.lambdaQuery()//Mybatis Plus 查询
                .eq(User::getUsername, user.getUsername())//判断库中的名字和前端传来的用户名是否相等
                .eq(User::getPassword, user.getPassword())//判断密码是否相等,再
                .list();//使用list()适应来返回一个list集合判断集合的长度就能判断是否找到数据

        User user1 = userService.selectByUsername(user.getUsername());
        return list.size() > 0 ? Result.success(JWTUtils.getToken(user1) ,1) : Result.fail();
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/mod")
    public Result mod(@RequestBody User user) {
        return userService.updateById(user) ? Result.success(null,1) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result deleted(Integer id) {
        return userService.removeById(id) ? Result.success(null,1) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }

    /**
     * 通过名字进行模糊查询
     *
     * @param
     * @return
     */
    @PostMapping("/selectByName")
    public Result selectClockByName(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String username = (String) param.get("username");
        Page<User> page = new Page(query.getPageSize(), query.getPageNum());
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(User::getUsername,username);
        IPage result = userService.page(page, lambdaQueryWrapper);
        //返回Result数据给前端通过状态码来判断是否成功
        return result!=null ? Result.success(result.getRecords() , (int) result.getTotal()) : Result.fail();
    }

    /**
     * 分页查询的全部用户
     *
     * @param query
     * @return
     */
    @PostMapping("/selectAllUser")
    public Result selectPage(@RequestBody QueryPageParam query) {
        //接收前端的pageNum数据和pageSize数据来确定分页的数据，如果没有那就按照自定义的分页数据
        Page<User> page = new Page(query.getPageNum(), query.getPageSize());
        //调用MybatisPlus中的
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        //把分页的信息和搜索全部数据用IPage接收并返回数据
        IPage result = userService.page(page, lambdaQueryWrapper.select());
        //返回Result数据给前端通过状态码来判断是否成功
        return result!= null ? Result.success(result.getRecords(), (int) result.getTotal()) : Result.fail();
    }

    @GetMapping("/verifyToken")
    public Result verifyToken(@RequestParam("token") String token){
        Map<String ,String> map = new HashMap<>();
        DecodedJWT verify = JWTUtils.verify(token);
        String username = verify.getClaim("username").asString();
        String power = verify.getClaim("power").asString();
        map.put("用户名" , username);
        map.put("权限" , power);
        System.out.println(power);
        return Result.success(map,1);
    }

}
