package com.tust.mapper;

import com.tust.entity.Basic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 基本信息 Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-11-11
 */
@Mapper
public interface BasicMapper extends BaseMapper<Basic> {

    Basic getBase(Integer id);


    int addBase(Basic basic);
}
