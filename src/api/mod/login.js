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

//验证token
export const ReqVerifyToken=(token)=>{
    return requests({
        url:"/user/verifyToken",
        method:"get",
        params:{
            token
        }
    })
}

//修改
export const ReqMod=(id,{username,password,phone,company,power})=>{
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

//删除用户
export const ReqDelete=(id)=>{
    return requests({
        url:'/user/delete',
        method:'get',
        params:{
            id
        }
    })
}

//查询所有用户
export const ReqSelectAllUser=(pageSize,pageNum)=>{
    return requests({
        url:'/user/selectAllUser',
        method:'post',
        data:{
            pageSize,pageNum
        }
    })
}