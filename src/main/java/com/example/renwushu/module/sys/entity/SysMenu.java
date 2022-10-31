package com.example.renwushu.module.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父菜单ID，一级菜单为0")
    private Long parentId;

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
    private Integer statu;

    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();

}
