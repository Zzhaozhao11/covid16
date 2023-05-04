package com.tust.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.comment.QueryPageParam;
import com.tust.comment.Result;
import com.tust.entity.Cure;
import com.tust.entity.Model;
import com.tust.service.BasicService;
import com.tust.service.CureService;
import com.tust.service.DetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 治愈 前端控制器
 * </p>
 *
 * @author wpf
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/cure")
public class CureController {
    @Autowired
    private CureService cureService;

    /**
     * 查询所有治愈者
     * @param query
     * @return
     */
    @PostMapping("/selectAllCure")
    public Result selectAllCure(@RequestBody QueryPageParam query) {
        Page<Model> page = new Page(query.getPageNum(), query.getPageSize());//接收前端的pageNum数据和pageSize数据来确定分页的数据，如果没有那就按照自定义的分页数据
        IPage result = cureService.selectAllCure(page);//cureService里的查询方法
        return result != null ? Result.success(result.getRecords(), (int) result.getTotal()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }

    /**
     * 按名字检索治愈的人
     * @param name
     * @return
     */
    @PostMapping("/selectAllCureByName")
    public Result selectAllCureByName(@RequestParam("name") String name) {
        List<Model> models = cureService.selectAllCureByName(name);
        return models.size()>0 ? Result.success(models , models.size()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }

    /**
     * 查看治愈详情信息
     * @param id
     * @return
     */
    @GetMapping("/getCureMessage")
    public Result getCureMessage(@RequestParam("id") Integer id){
        Model cureMessage = cureService.getCureMessage(id);
        return cureMessage!=null ? Result.success(cureMessage, 1) : Result.fail();
    }
    /**
     * 转为治愈
     * @param basicId
     * @param outDate
     * @param status
     * @return
     */
    @GetMapping("/toCure")
    public Result toCure(@RequestParam("basicId") Integer basicId
            ,@RequestParam("outDate") String outDate
            ,@RequestParam("status") String status) {
        Cure cure = new Cure();
        cure.setBasicId(basicId);
        cure.setOutDate(outDate);
        cure.setStatus(status);
        return cureService.save(cure) ? Result.success(cure, 1) : Result.fail();
    }
}
