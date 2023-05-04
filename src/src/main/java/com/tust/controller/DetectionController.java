package com.tust.controller;


import com.tust.comment.Result;
import com.tust.entity.Detection;
import com.tust.service.DetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 检测 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/detection")
public class DetectionController {

    @Autowired
    private DetectionService detectionService;

    /**
     * 添加检测信息
     * @param basicId
     * @param detectionDate
     * @param nuclein
     * @param ct
     * @return
     */
    @GetMapping("/insertDetection")
    public Result insertDetection(@RequestParam("basicId") Integer basicId
            ,@RequestParam("detectionDate") String detectionDate
            ,@RequestParam("nuclein") String nuclein
            ,@RequestParam("ct")String ct){
        Detection detection = new Detection();
        detection.setBasicId(basicId);
        detection.setDetectionDate(detectionDate);
        detection.setNuclein(nuclein);
        detection.setCt(ct);
        return detectionService.save(detection) ? Result.success(detection, 1) : Result.fail();

    }

    /**
     * 查看检测信息
     * @param id
     * @return
     */
    @GetMapping("/getDetection")
    public Result getDetection(@RequestParam("id") Integer id){
        List<Detection> detection = detectionService.getDetection(id);
        return detection!=null ? Result.success(detection, detection.size()) : Result.fail();
    }

}
