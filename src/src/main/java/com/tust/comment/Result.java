package com.tust.comment;

import lombok.Data;

@Data
public class Result {
    /**
     * 数据
     */
    private Object data;
    private Integer code;//编码200/400
    private String msg;//信息
    private Integer total;//总计

   public static Result fail(){
       return result(400,null,"失败",0);
   }
   public static Result fail(Integer code , Object data , String msg){
       return result(code,data,msg,0);
   }

   public static Result success(Object data,Integer total){
       return result(200,data,"成功",total);
   }

    private static Result result(Integer code,Object data, String msg,Integer total) {
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        result.setTotal(total);
        return  result;
    }


}
