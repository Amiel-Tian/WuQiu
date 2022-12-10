package com.example.renwushu.module.renwushu.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.renwushu.common.base.BaseEntity;
import com.example.renwushu.module.sys.entity.SysMenu;
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
 * @since 2022-12-09
 */
@Getter
@Setter
@TableName("n_business_info")
@ApiModel(value = "BusinessInfo对象", description = "")
public class BusinessInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("业务名称")
    private String name;

    @ApiModelProperty("父id")
    private String parentId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("附件")
    private String attachements;

    @ApiModelProperty("业务状态")
    private String businessStatus;

    @ApiModelProperty("操作人")
    private String createBy;

    @ApiModelProperty("操作人组织")
    private String createByOrgId;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("修改时间")
    private Date updateDate;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("排序")
    private Integer sort;


    @TableField(exist = false)
    private List<BusinessInfo> children = new ArrayList<>();
}
