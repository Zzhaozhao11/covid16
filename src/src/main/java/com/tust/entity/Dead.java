package com.tust.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zzhaozhao
 * @date 2023/2/28
 * @apiNote
 */
@Data
public class Dead {
    private Integer BasicId;

    @ApiModelProperty(value = "出院日期")
    private String DeadDate;
}
