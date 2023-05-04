package com.tust.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.entity.Information;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tust.entity.Model;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 密切接触者信息 Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Mapper
public interface InformationMapper extends BaseMapper<Information> {

    IPage selectAllInformationService(Page<Model> page);

    int addEnd(Integer basicId);

    Information getInformationMessage(Integer id);

    int addInformation(Information information);
}
