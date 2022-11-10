package com.example.renwushu.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.example.renwushu.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author RedStar
 * @since 2022-11-10
 */
@Getter
@Setter
@TableName("sys_dict_type")
@ApiModel(value = "SysDictType对象", description = "字典类型表")
public class SysDictType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典类型表")
    private String id;

    @ApiModelProperty("字典名称")
    private String dictName;

    @ApiModelProperty("字典编码")
    private String dictType;

    @ApiModelProperty("字典类型：0系统、1业务")
    private String isSys;

    @ApiModelProperty("状态：0正常 1删除 2停用")
    private String status;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("最后一次更新者")
    private String updateBy;

    @ApiModelProperty("最后一次更新时间")
    private Date updateDate;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("父级编号")
    private String parentId;

    @ApiModelProperty("所有父级编号")
    private String parentIds;

    @ApiModelProperty("本级排序号（升序）")
    private BigDecimal treeSort;

    @ApiModelProperty("级联，0否，1是")
    private String cascaded;


}
