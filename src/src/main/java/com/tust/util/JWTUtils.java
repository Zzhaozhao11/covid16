package com.tust.util;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tust.entity.User;

import java.util.Calendar;

public class JWTUtils {

    private static final String SIGN = "!@#$%12345";

    /**
     * 生成token
     * header.payload.sign
     * @param user
     * @return
     */
    public static String getToken(User user){
        Calendar instance = Calendar.getInstance();
        //设置过期时间 七天过期
        instance.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("username" , user.getUsername())
                .withClaim("power" , user.getPower());  //payload
             String token = builder.withExpiresAt(instance.getTime())//指定过期时间
                .sign(Algorithm.HMAC256(SIGN));//盐
        return token;
    }

    /**
     * 验证token合法性
     * @param token
     */
    public static  DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
