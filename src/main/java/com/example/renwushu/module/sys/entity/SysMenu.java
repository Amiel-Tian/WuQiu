package com.example.renwushu.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-10-30
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "")
public class SysMenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    @ApiModelProperty("父菜单ID，一级菜单为0")
    private String parentId;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单URL")
    private String path;

    @ApiModelProperty("授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    private String component;

    @ApiModelProperty("类型     0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("菜单创建时间")
    private Date createdTime;

    @ApiModelProperty("菜单修改时间")
    private Date updatedTime;

    @ApiModelProperty("状态1可用|0不可用")
    private Integer status;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("可见设备端（空：所有  PC：仅PC可见  APP：仅移动端 可见）")
    private String device;
    @ApiModelProperty("vant图标")
    private String vantIcon;

    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();

}
