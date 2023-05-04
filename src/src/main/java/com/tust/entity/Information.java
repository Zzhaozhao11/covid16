package com.tust.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 密切接触者信息
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Information对象", description="密切接触者信息")
public class Information implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer basicId;

    @ApiModelProperty(value = "接触源")
    private String source;

    @ApiModelProperty(value = "隔离地址")
    private String divideAddress;

    @ApiModelProperty(value = "隔离开始时间")
    private String divideDate;


}
