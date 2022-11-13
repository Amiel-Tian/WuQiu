package com.example.renwushu.module.renwu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.example.renwushu.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author RedStar
 * @since 2022-11-13
 */
@Getter
@Setter
@TableName("n_renwu_project")
@ApiModel(value = "RenwuProject对象", description = "")
public class RenwuProject extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("合同编号")
    private String num;

    @ApiModelProperty("客户名")
    private String name;

    @ApiModelProperty("负责人")
    private String person;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("修改时间")
    private Date updatedTime;

    @ApiModelProperty("用户状态 1可用|0不可用")
    private Integer status;


}
