import requests from '../request'

//新建病人基本信息
export const reqAddBasic=({name,personId,age,gender,houseAddress,phone})=>{
    return requests({
        url: '/basic/addBasic',
        method: 'get',
    params: {
      name,personId,age,gender,houseAddress,phone
    }
    })
}
//按序号查询基本信息
export const reqGetBasic=(id)=>{
    return requests({
        url: '/basic/getBasic',
        method: 'get',
        params:{
            id
        }
    })
}

//**********************************治愈部分******************************

//查询所有治愈者
export const ReqSelectAllCure=(pageSize,pageNum)=>{
    return requests({
        url: '/cure/selectAllCure',
        method: 'post',
        data:{
            pageSize,pageNum
        }
    })
}
//按名字检索治愈的人
export const ReqSelectAllCureByName=(name)=>{
    return requests({
        url: '/cure/selectAllCureByName',
        method: 'post',
        params:{
            name
        }
    })
}


//查看治愈详情信息
export const ReqGetCureMessage=(id)=>{
    return requests({
        url:'/cure/getCureMessage',
        method:'get',
        params:{
            id
        }
    })
}

//转为治愈
export const ReqToCare=(basicId,outDate)=>{
    return requests({
      url:'/cure/toCure',
      method:'get',
      params:{
        basicId,outDate,
        status:'治愈'
      }
    })
}


//查找所有去世的人
export const ReqSelectAllDead=(pageSize,pageNum)=>{
    return requests({
        url:'/dead/selectAllDead',
        method:"post",
        data:{
           pageSize,
           pageNum
        }
    })
}



//按名字检索去世的人
export const ReqSelectDeadByName=(name)=>{
    return requests({
        url:'/dead/selectDeadByName',
        method:'post',
        params:{
            name
        }
    })
}


//转为死亡
export const ReqToDead=(basicId,deadDate)=>{
    return requests({
        url:'/dead/toDead',
        method:'get',
        params:{
            basicId,
            deadDate
        }
    })
}

//添加检测信息
export const ReqInsertDetetion=(basicId,detectionDate,nuclein,ct)=>{
    return requests({
        url:'/detection/insertDetetion',
        method:'get',
        params:{
            basicId,detectionDate,nuclein,ct
        }
    })
}

//查看检测信息
export const ReqGetDetetion=(id)=>{
    return requests({
        url:'/detection/getDetetion',
        method:'get',
        params:{
            id
        }
    })
}

//查询所有密接的人
export const ReqSelectAllInformationService=(pageSize,pageNum)=>{
    return requests({
        url:'/information/selectAllInformationService',
        method:'post',
        data:{
            pageSize,pageNum
        }
    })
}


//隔离完毕
export const ReqAddEnd=(Id)=>{
    return requests({
        url:'/information/addEnd',
        method:'get',
        params:{
            Id
        }
    })
}

//查看密接详情
export const ReqGetInformationMessage=(id)=>{
    return requests({
        url:'/information/getInformationMessage',
        method:'get',
        params:{
            id
        }
    })
}


//添加密接信息
export const ReqAddInformation=(basicId,{source,divideAddress,divideDate})=>{
    return requests({
        url:'/information/addInformation'   ,
        method:'get',
        params:{
            basicId,source,divideAddress,divideDate
        }
    })
}

//查询所有病人
export const ReqSelectAllPatient=(pageSize,pageNum)=>{
    return requests({
        url:'/patient/selectAllPatient',
        method:'post',
        data:{
            pageSize,pageNum
        }
    })
}


//按名字检索所有病人
export const ReqSelectPatientByName=(name)=>{
    return requests({
        url:'/patient/selectPatientByName',
        method:'post',
        params:{
            name
        }
    })
}


//添加病人详细信息
export const ReqAddManage=(id,{inDate,symptoms,condition,comments,source,hospital})=>{
    return requests({
        url:'/patient/addManage',
        method:'get',
        params:{
            basicId:id,
            inDate,source,symptoms,condition,comments,hospital
        }
    })
}

