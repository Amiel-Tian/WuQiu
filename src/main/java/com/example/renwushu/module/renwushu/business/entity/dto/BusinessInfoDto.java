package com.example.renwushu.module.renwushu.business.entity.dto;

import com.example.renwushu.module.sys.entity.dto.SysMenuDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BusinessInfoDto {
    private String id;
    private String parentId;
    private String name;
    private String icon;
    private Integer sort;
    List<BusinessInfoDto> children = new ArrayList<>();
}
