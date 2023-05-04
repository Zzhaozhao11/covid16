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
 * 
 * </p>
 *
 * @author zyz
 * @since 2022-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Patient对象", description="")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "basic_id", type = IdType.AUTO)
    private Integer basicId;

    @ApiModelProperty(value = "感染源 ")
    private String source;

    @ApiModelProperty(value = "住院时间")
    private String inDate;

    @ApiModelProperty(value = "症状")
    private String symptoms;

    @ApiModelProperty(value = "医院")
    private String hospital;

    @ApiModelProperty(value = "是否为重症")
    private String condition;

    @ApiModelProperty(value = "备注 ")
    private String comments;


}
