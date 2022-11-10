package com.example.renwushu.module.renwu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.example.renwushu.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author RedStar
 * @since 2022-11-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("n_renwu_info")
@ApiModel(value = "RenwuInfo对象", description = "")
public class RenwuInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("开始时间")
    private Date startDate;

    @ApiModelProperty("结束时间")
    private Date endDate;

    @ApiModelProperty("客户名称")
    private String customerName;

    @ApiModelProperty("合同编号")
    private String contractNo;

    @ApiModelProperty("申请人")
    private String applicant;

    @ApiModelProperty("接收人")
    private String recipient;

    @ApiModelProperty("工作时长")
    private Integer workTime;

    @ApiModelProperty("内容")
    private String context;

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

    @TableField(exist = false)
    private String year;
    @TableField(exist = false)
    private String month;


}
