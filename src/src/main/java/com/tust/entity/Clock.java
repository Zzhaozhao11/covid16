package com.tust.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 打卡
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Clock对象", description="打卡")
public class Clock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "现在地址")
    private String location;

    @ApiModelProperty(value = "健康状况")
    private String health;

    @ApiModelProperty(value = "体温")
    private String temperature;

    @ApiModelProperty(value = "打卡时间")
    private String clockDate;

    @ApiModelProperty(value = "是否到达过高风险地区")
    private String danger;

    @ApiModelProperty(value = "是否接触过疑似或者确诊病例")
    private String contact;

    @ApiModelProperty(value = "备注")
    private String comments;


}
