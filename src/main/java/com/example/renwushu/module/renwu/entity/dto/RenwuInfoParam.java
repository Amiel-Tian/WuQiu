package com.example.renwushu.module.renwu.entity.dto;

import com.example.renwushu.module.renwu.entity.RenwuInfo;
import lombok.Data;

@Data
public class RenwuInfoParam extends RenwuInfo {
    private String orderByType;
    private String orderBy;
}
