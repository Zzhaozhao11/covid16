import requests from '../request'


//注册
export const ReqRegister=({username,password,phone,company,power})=>{
      return requests({
        url:'/user/register',
        method:'post',
        data:{
            username,password,phone,company,power
        }
      })
}

//登录
export const ReqLogin=(username,password)=>{
    return requests({
        url:"/user/login",
        method:"post",
        data:{
            username,password
        }
    })
}


//修改
export const ReqMod=({id,username,password,phone,company,power})=>{
    return requests({
        url:"/user/mod",
        method:"post",
        data:{
            id,username,password,phone,company,power
        }
    })
}


//通过名字检索
export const ReqSelectByName=(username)=>{
    return requests({
        url:"/user/selectByName",
        method:"post",
        data:{
            pageSize:1,
            pageNum:1,
            param:{
                username
            }
        }
    })
}