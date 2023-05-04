package com.tust.service.impl;

import com.tust.comment.Result;
import com.tust.entity.Detection;
import com.tust.mapper.DetectionMapper;
import com.tust.service.DetectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 检测 服务实现类
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Service
public class DetectionServiceImpl extends ServiceImpl<DetectionMapper, Detection> implements DetectionService {

    @Autowired
    public DetectionMapper detectionMapper;


    @Override
    public List<Detection> getDetection(Integer id) {
        return detectionMapper.getDetection(id);
    }
}
