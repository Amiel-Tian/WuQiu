package com.example.renwushu.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2023-02-05
 */
@Getter
@Setter
@TableName("sys_user_organ")
@ApiModel(value = "SysUserOrgan对象", description = "")
public class SysUserOrgan implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String organId;


}
