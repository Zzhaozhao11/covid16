package com.tust.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.comment.QueryPageParam;
import com.tust.comment.Result;
import com.tust.entity.Dead;
import com.tust.entity.Model;
import com.tust.service.DeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 死亡 前端控制器
 * </p>
 *
 * @author wpf
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/dead")
public class DeadController {
    @Autowired
    private DeadService deadService;

    /**
     * 查找所有的去世的人
     * @param query
     * @return
     */
    @PostMapping("/selectAllDead")
    public Result selectAllDead(@RequestBody QueryPageParam query) {
        Page<Model> page = new Page(query.getPageNum(), query.getPageSize());//接收前端的pageNum数据和pageSize数据来确定分页的数据，如果没有那就按照自定义的分页数据
        IPage result = deadService.selectAllDead(page);
        return result != null ? Result.success(result.getRecords(), (int) result.getTotal()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }

    /**
     * 按名字检索去世的人
     * @param name
     * @return
     */
    @PostMapping("/selectDeadByName")
    public Result selectDeadByName(@RequestParam("name") String name) {
        List<Model> models = deadService.selectDeadByName(name);
        return models.size()>0 ? Result.success(models , models.size()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }
    /**
     * 转为死亡
     * @param basicId
     * @param deadDate
     * @return
     */
    @GetMapping("/toDead")
    public Result toDead(@RequestParam("basicId") Integer basicId
            ,@RequestParam("deadDate") String deadDate) {
        Dead dead = new Dead();
        dead.setBasicId(basicId);
        dead.setDeadDate(deadDate);
        return deadService.save(dead) ? Result.success(dead, 1) : Result.fail();

    }
}
