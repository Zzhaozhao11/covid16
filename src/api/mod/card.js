import requests from '../request'
//添加打卡信息
export const reqAddCard=({name,location,health,temperature,clockDate,danger,contact,comments})=>{
    console.log(name,location,health,temperature,clockDate,danger,contact,comments);
    return requests({
        url:'/clock/addClock',
        method:'get',
        params:{
            Id:'1',
        name,location,health,temperature,clockDate,danger,contact,comments
        }
    })
}
//查询所有打卡记录
export const reqGetCard=(pageSize,pageNum)=>{
    return requests({
        url:'/clock/selectAllClock',
        method:'post',
        data:{
           pageSize,
           pageNum
        }
    })
}

//按名字检索打卡记录
export const reqSearchCard=(pageSize,pageNum,name)=>{
    return requests({
        url:'/clock/selectClockByName',
        method:'post',
        data:{
            pageSize,pageNum,
            param:{
                name
            }
        }
    })
}

