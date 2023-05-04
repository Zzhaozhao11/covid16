package com.tust.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Model {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String personId;


    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "住宅")
    private String houseAddress;

    @ApiModelProperty(value = "住院时间")
    private String inDate;

    @ApiModelProperty(value = "出院日期")
    private String outDate;

    @ApiModelProperty(value = "感染源 ")
    private String source;

    @ApiModelProperty(value = "隔离地址")
    private String divideAddress;

    @ApiModelProperty(value = "隔离开始时间")
    private String divideDate;

   @ApiModelProperty(value = "死亡时间")
    private String deadDate;

    @ApiModelProperty(value = "症状")
    private String symptoms;

    @ApiModelProperty(value = "医院")
    private String hospital;

    @ApiModelProperty(value = "是否为重症")
    private String condition;

    @ApiModelProperty(value = "备注 ")
    private String comments;
}
