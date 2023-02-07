package com.example.renwushu.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.renwushu.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author RedStar
 * @since 2023-02-05
 */
@Getter
@Setter
@TableName("sys_organ")
@ApiModel(value = "SysOrgan对象", description = "")
public class SysOrgan extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("父ID，一级为0")
    private String parentId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("编码")
    private String code;

    private String component;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("修改时间")
    private Date updatedTime;

    @ApiModelProperty("状态1可用|0不可用")
    private Integer status;

    @ApiModelProperty("排序")
    private Integer sort;

    @TableField(exist = false)
    private List<SysOrgan> children = new ArrayList<>();
}
