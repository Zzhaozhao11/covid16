package com.tust.mapper;

import com.tust.entity.Detection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 检测 Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Mapper
public interface DetectionMapper extends BaseMapper<Detection> {

    List<Detection> getDetection(Integer id);
}
