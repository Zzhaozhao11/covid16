package com.tust.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 检测
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Detection对象", description="检测")
public class Detection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer basicId;

    @ApiModelProperty(value = "检测日期")
    private String detectionDate;

    @ApiModelProperty(value = "核酸检测")
    private String nuclein;

    @ApiModelProperty(value = "ct检测")
    private String ct;


}
