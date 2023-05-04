package com.tust.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 治愈
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Cure对象", description="治愈")
public class Cure implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer basicId;

    @ApiModelProperty(value = "出院日期")
    private String outDate;

    @ApiModelProperty(value = "现状 ")
    private String status;


}
