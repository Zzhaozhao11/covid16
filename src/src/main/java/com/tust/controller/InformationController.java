package com.tust.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.comment.QueryPageParam;
import com.tust.comment.Result;
import com.tust.entity.Basic;
import com.tust.entity.Information;
import com.tust.entity.Model;
import com.tust.entity.Patient;
import com.tust.service.BasicService;
import com.tust.service.InformationService;
import com.tust.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 密切接触者信息 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/information")
public class InformationController {
    @Autowired
    private InformationService informationService;

    /**
     * 查询所有密切接触的人
     * @param query
     * @return
     */
    @PostMapping("/selectAllInformationService")
    public Result selectAllInformationService(@RequestBody QueryPageParam query) {
        Page<Model> page = new Page(query.getPageNum(), query.getPageSize());//接收前端的pageNum数据和pageSize数据来确定分页的数据，如果没有那就按照自定义的分页数据
        IPage result = informationService.selectAllInformationService(page);
        return result != null ? Result.success(result.getRecords(), (int) result.getTotal()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }

    /**
     * 隔离完毕
     * @param Id
     * @return
     */
    @GetMapping("/addEnd")
    public Result addEnd(@RequestParam("Id") Integer Id){
        return   informationService.addEnd(Id) ? Result.success(null, 1) : Result.fail();
    }

    /**
     * 查看密接的详情
     * @param id
     * @return
     */
    @GetMapping("/getInformationMessage")
    public Result getInformationMessage(@RequestParam("id") Integer id){
        Information cureMessage = informationService.getInformationMessage(id);
        return cureMessage!=null ? Result.success(cureMessage, 1) : Result.fail();
    }

    /**
     * 添加密接信息
     * @param basicId
     * @param source
     * @param divideAddress
     * @param divideDate
     * @return
     */
    @GetMapping("/addInformation")
    public Result addInformation(@RequestParam("basicId") Integer basicId
            , @RequestParam("source") String source
            , @RequestParam("divideAddress") String divideAddress
            , @RequestParam("divideDate") String divideDate){
        Information information = new Information();
        information.setBasicId(basicId);
        information.setSource(source);
        information.setDivideAddress(divideAddress);
        information.setDivideDate(divideDate);
        return informationService.save(information) ?Result.success(information,1) : Result.fail();
    }

}
