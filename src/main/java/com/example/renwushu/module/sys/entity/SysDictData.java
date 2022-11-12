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
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author RedStar
 * @since 2022-11-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_dict_data")
@ApiModel(value = "SysDictData对象", description = "字典数据表")
public class SysDictData extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典数据主键")
    private String id;

    @ApiModelProperty("字典类型表")
    private String dictId;

    @ApiModelProperty("标签")
    private String dictKey;

    @ApiModelProperty("键值")
    private String dictValue;

    @ApiModelProperty("排序")
    private Integer treeSort;

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

    @ApiModelProperty("上级ID")
    private String parentId;

    @ApiModelProperty("上级ID合集")
    private String parentIds;


}
