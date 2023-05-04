package com.tust.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.entity.Dead;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tust.entity.Model;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 死亡 Mapper 接口
 * </p>
 *
 * @author wpf
 * @since 2022-11-10
 */
@Mapper
public interface DeadMapper extends BaseMapper<Dead> {

    IPage selectAllDead(Page<Model> page);

    List<Model> selectDeadByName(String name);
}
