package com.example.renwushu.module.chat.messages.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.renwushu.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author RedStar
 * @since 2023-02-09
 */
@Getter
@Setter
@TableName("n_messages")
@ApiModel(value = "Messages对象", description = "")
public class Messages extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("发送者id")
    private String sendId;

    @ApiModelProperty("接收者id")
    private String receiveId;

    @ApiModelProperty("消息内容")
    private String message;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("发送时间")
    private Date sendDate;

    @ApiModelProperty("查看时间")
    private Date seeDate;

    @ApiModelProperty("群人员id")
    private String groupPerId;

    @ApiModelProperty("群人员")
    @TableField(exist = false)
    private String users;

}
