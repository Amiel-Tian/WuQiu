package com.example.renwushu.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件共享表
 * </p>
 *
 * @author RedStar
 * @since 2022-08-01
 */
@Getter
@Setter
@TableName("sys_file")
@ApiModel(value = "SysFile对象", description = "文件共享表")
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("文件名称")
    private String fileName;

    @ApiModelProperty("文件原始名称")
    private String originName;

    @ApiModelProperty("文件扩展名")
    private String fileExt;

    @ApiModelProperty("文件大小")
    private Long fileSize;

    @ApiModelProperty("文件路径")
    private String fileRoute;

    @ApiModelProperty("链接地址")
    private String fileLink;

    @ApiModelProperty("相对地址")
    private String relativeLink;

    @ApiModelProperty("标签")
    private String label;

    @ApiModelProperty("文件所属文件夹")
    private String folderId;

    @ApiModelProperty("上传文件人组织")
    private String orgId;

    @ApiModelProperty("逻辑删除(未删除:0 删除1)")
    private String status;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("更新者")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private Date updateDate;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("业务主键")
    private String businessId;

    @ApiModelProperty("附件UUID")
    private String uuid;

    @ApiModelProperty("策略")
    private String strategy;

    @ApiModelProperty("上传文件人组织树")
    private String orgTree;

    @ApiModelProperty("是否为临时文件（0：是；1：否）")
    private String temporary;


}
