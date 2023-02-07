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
 * 
 * </p>
 *
 * @author RedStar
 * @since 2023-02-06
 */
@Getter
@Setter
@TableName("sys_websocket")
@ApiModel(value = "SysWebsocket对象", description = "")
public class SysWebsocket implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("发送人id")
    private String sendId;

    @ApiModelProperty("接收人id")
    private String receiveId;

    @ApiModelProperty("消息内容")
    private String message;

    @ApiModelProperty("发送时间")
    private Date sendDate;


}
