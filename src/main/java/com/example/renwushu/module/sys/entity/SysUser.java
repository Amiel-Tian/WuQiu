package com.example.renwushu.module.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.renwushu.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class SysUser  extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("登录名")
    private String loginname;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("用户创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime createdTime;

    @ApiModelProperty("用户修改时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime updatedTime;

    @ApiModelProperty("用户上次登录时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("用户状态 1可用|0不可用")
    private Integer status;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    private List<String> roleIdList;

    @ApiModelProperty("组织列表")
    @TableField(exist = false)
    private List<String> organIdList;

    @ApiModelProperty("验证密码")
    @TableField(exist = false)
    private String passwordVer;

}
