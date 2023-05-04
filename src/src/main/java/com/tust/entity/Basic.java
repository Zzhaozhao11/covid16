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
 * 基本信息
 * </p>
 *
 * @author zyz
 * @since 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Basic对象", description="基本信息")
public class Basic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String personId;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "住宅")
    private String houseAddress;

    @ApiModelProperty(value = "手机号")
    private String phone;


}
