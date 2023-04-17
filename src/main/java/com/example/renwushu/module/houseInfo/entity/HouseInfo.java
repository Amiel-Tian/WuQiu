package com.example.renwushu.module.houseInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 查看房子基础表
 * </p>
 *
 * @author RedStar
 * @since 2022-07-26
 */
@Getter
@Setter
@TableName("house_info")
@ApiModel(value = "HouseInfo对象", description = "查看房子基础表")
public class HouseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("小区名称")
    private String houseName;

    @ApiModelProperty("小区地址")
    private String houseAddess;

    @ApiModelProperty("小区地址经度")
    private String houseAddessLon;

    @ApiModelProperty("小区地址纬度")
    private String houseAddessLat;

    @ApiModelProperty("房间面积")
    private String houseArea;

    @ApiModelProperty("房间类型（几室几厅）")
    private String houseType;

    @ApiModelProperty("房间墙体")
    private String houseWall;

    @ApiModelProperty("房间卫生")
    private String houseHygiene;

    @ApiModelProperty("原价")
    private Long originalPrice;

    @ApiModelProperty("价格")
    private Long price;

    @ApiModelProperty("付款类型（压几付几）")
    private String paymentType;

    @ApiModelProperty("付款具体时间")
    private String paymentTime;

    @ApiModelProperty("是否有物业费")
    private String propertyFeeOrNot;

    @ApiModelProperty("物业费")
    private Long propertyFee;

    @ApiModelProperty("物业费类型")
    private String propertyFeeType;

    @ApiModelProperty("是否有网费")
    private String networkFeeOrNot;

    @ApiModelProperty("网费")
    private Long networkFee;

    @ApiModelProperty("网费类型")
    private String networkFeeType;

    @ApiModelProperty("是否有管理费")
    private String manageFeeOrNot;

    @ApiModelProperty("管理费")
    private Long manageFee;

    @ApiModelProperty("管理费类型")
    private String manageFeeType;

    @ApiModelProperty("是否有中介费")
    private String agencyFeeOrNot;

    @ApiModelProperty("中介费")
    private Long agencyFee;

    @ApiModelProperty("中介费类型")
    private String agencyFeeType;

    @ApiModelProperty("是否有阳台")
    private String balconyOrNot;

    @ApiModelProperty("阳台状态")
    private String balconyStatus;

    @ApiModelProperty("阳台附件")
    private String balconyFile;

    @ApiModelProperty("是否有冰箱")
    private String refrigeratorOrNot;

    @ApiModelProperty("冰箱状态")
    private String refrigeratorStatus;

    @ApiModelProperty("冰箱附件")
    private String refrigeratorFile;

    @ApiModelProperty("是否有厨房")
    private String kitchenOrNot;

    @ApiModelProperty("厨房状态")
    private String kitchenStatus;

    @ApiModelProperty("厨房附件")
    private String kitchenFile;

    @ApiModelProperty("是否有独立厕所")
    private String toiletOrNot;

    @ApiModelProperty("厕所状态")
    private String toiletStatus;

    @ApiModelProperty("厕所附件")
    private String toiletFile;

    @ApiModelProperty("是否有空调")
    private String airConditionerOrNot;

    @ApiModelProperty("空调状态")
    private String airConditionerStatus;

    @ApiModelProperty("是否有暖气")
    private String heatingOrNot;

    @ApiModelProperty("是否有地暖")
    private String floorHeatingOrNot;

    @ApiModelProperty("租房人（个人、公司名）")
    private String renter;

    @ApiModelProperty("附件")
    private String attachements;
    @ApiModelProperty("备注")
    private String remark;

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



    @ApiModelProperty("电费")
    private String electricityFee;

    @ApiModelProperty("电费类型")
    private String electricityFeeType;

    @ApiModelProperty("是否有电费")
    private String electricityFeeOrNot;

    @ApiModelProperty("水费")
    private String waterFee;

    @ApiModelProperty("水费类型")
    private String waterFeeType;

    @ApiModelProperty("是否有水费")
    private String waterFeeOrNot;

    public static String CREATE_DATE = "create_date";
    public static String STATUS = "status";
}
