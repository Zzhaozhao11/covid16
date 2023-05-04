package com.tust.mapper;

import com.tust.entity.Clock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 打卡 Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Mapper
public interface ClockMapper extends BaseMapper<Clock> {

}
