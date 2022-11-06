package com.example.renwushu.module.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.renwushu.common.base.BaseEntity;
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
 * @since 2022-10-30
 */
@Getter
@Setter
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "")
public class SysRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色")
    private String code;

    @ApiModelProperty("角色创建时间")
    private Date createdTime;

    @ApiModelProperty("角色修改时间")
    private Date updatedTime;

    @ApiModelProperty("角色状态 1可用|0不可用")
    private Integer statu;

    @TableField(exist = false)
    @ApiModelProperty("权限菜单列表")
    private List<String> menuIdList;


}
