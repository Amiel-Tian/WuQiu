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
 * 简历基础信息表
 * </p>
 *
 * @author RedStar
 * @since 2022-08-27
 */
@Getter
@Setter
@TableName("n_resume_info")
@ApiModel(value = "ResumeInfo对象", description = "简历基础信息表")
public class ResumeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身高")
    private String height;

    @ApiModelProperty("专业")
    private String major;

    @ApiModelProperty("民族")
    private String nation;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("电话")
    private String telephone;

    @ApiModelProperty("邮箱")
    private String mailbox;

    @ApiModelProperty("当前住址")
    private String currentAddress;

    @ApiModelProperty("学历")
    private String education;

    @ApiModelProperty("毕业院校")
    private String graduationSchool;

    @ApiModelProperty("头像")
    private String headPortrait;

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


}
