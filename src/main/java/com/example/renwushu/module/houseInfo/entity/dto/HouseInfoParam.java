package com.example.renwushu.module.houseInfo.entity.dto;

import com.example.renwushu.module.houseInfo.entity.HouseInfo;
import lombok.Data;

@Data
public class HouseInfoParam extends HouseInfo {

    private String orderByType;
    private String orderBy;
}
