package com.tust.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.entity.Cure;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tust.entity.Model;
import org.apache.ibatis.annotations.Mapper;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

/**
 * <p>
 * 治愈 Mapper 接口
 * </p>
 *
 * @author wpf
 * @since 2022-11-10
 */
@Mapper
public interface CureMapper extends BaseMapper<Cure> {

    IPage selectAllCure(Page<Model> page);


    Model getCureMessage(Integer id);

    List<Model> selectAllCureByName(String name);
}
