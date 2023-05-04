package com.tust.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tust.entity.Basic;
import com.tust.entity.Dead;
import com.tust.entity.Model;
import com.tust.entity.Patient;
import com.tust.mapper.DeadMapper;
import com.tust.mapper.PatientMapper;
import com.tust.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public IPage selectALLPatient(IPage<Model> page) {
        return patientMapper.selectAllPatient(page);
    }

    @Override
    public List<Model> selectPatientByName(String name) {
        return patientMapper.selectPatientByName(name);
    }

    @Override
    public Patient getManage(Integer id) {
        return patientMapper.getManage(id);
    }
    @Override
    public boolean addManage(Patient patient) {
        return patientMapper.addManage(patient)>0;
    }


}
