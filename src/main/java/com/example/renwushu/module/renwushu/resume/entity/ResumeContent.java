package com.example.renwushu.module.renwushu.resume.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 简历内容
 * </p>
 *
 * @author RedStar
 * @since 2022-09-08
 */
@Getter
@Setter
@TableName("n_resume_content")
@ApiModel(value = "ResumeContent对象", description = "简历内容")
public class ResumeContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("简历id")
    private String resumeId;

    @ApiModelProperty("开始时间")
    private Date startDate;

    @ApiModelProperty("结束时间")
    private Date endDate;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("标签")
    private String tags;

    @ApiModelProperty("标记")
    private String labels;

    @ApiModelProperty("类别")
    private String type;

    @ApiModelProperty("分组（教育经历，项目内容）")
    private String groupings;

    @ApiModelProperty("连接")
    private String links;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("附件")
    private String attachements;

    @ApiModelProperty("业务状态")
    private String businessStatus;

    @ApiModelProperty("流程实例标识")
    private String instanceId;

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
    private String status;

    public static String RESUME_ID = "resume_id";
    public static String SORT_ = "sort";
    public static String STATUS = "status";

}
