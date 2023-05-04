package com.tust.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.entity.Basic;
import com.tust.entity.Model;
import com.tust.entity.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

    IPage selectAllPatient(IPage<Model> page);

    Patient getManage(Integer id);

    List<Model> selectPatientByName(String name);

    int addManage(Patient patient);
}
