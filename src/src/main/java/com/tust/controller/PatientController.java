package com.tust.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.comment.QueryPageParam;
import com.tust.comment.Result;
import com.tust.entity.*;
import com.tust.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    /**
     * 查询所有病人
     * @param query
     * @return
     */
    @PostMapping("/selectAllPatient")
    public Result selectAllUser(@RequestBody QueryPageParam query) {
        Page<Model> page = new Page(query.getPageNum(), query.getPageSize());//接收前端的pageNum数据和pageSize数据来确定分页的数据，如果没有那就按照自定义的分页数据
        IPage result = patientService.selectALLPatient(page);
        return result != null ? Result.success(result.getRecords(), (int) result.getTotal()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }
    /**
     * 按名字检索所有病人
     * @param name
     * @return
     */
    @PostMapping("/selectPatientByName")
    public Result selectPatientByName(@RequestParam("name") String name) {
        List<Model> models = patientService.selectPatientByName(name);
        return models.size()>0 ? Result.success(models , models.size()) : Result.fail();//返回Result数据给前端通过状态码来判断是否成功
    }
    /**
     * 添加病人详细信息
     * @param inDate
     * @param symptoms
     * @param condition
     * @param comments
     * @param source
     * @param hospital
     * @return
     */
    @GetMapping("/addManage")
    public Result addManage(@RequestParam("basicId") Integer basicId
            , @RequestParam("inDate") String inDate
            ,@RequestParam("source") String source
            , @RequestParam("symptoms") String symptoms
            , @RequestParam("condition") String condition
            , @RequestParam("comments") String comments
            , @RequestParam("hospital") String hospital) {
        Patient patient = new Patient();
        patient.setBasicId(basicId);
        patient.setSource(source);
        patient.setInDate(inDate);
        patient.setSymptoms(symptoms);
        patient.setHospital(hospital);
        patient.setCondition(condition);
        patient.setComments(comments);
        return patientService.addManage(patient) ? Result.success(patient, 1) : Result.fail();
    }
    /**
     * 查看此人管理信息
     * @param id
     * @return
     */
    @GetMapping("/getManage")
    public Result getManage(@RequestParam("id") Integer id){
        Patient manage = patientService.getManage(id);
        return manage!=null ? Result.success(manage, 1) : Result.fail();
    }

}
