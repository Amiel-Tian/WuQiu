package com.example.renwushu.module.sys.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysOrganDto {
    private String id;
    private String name;
    private String icon;
    private String code;
    private Integer type;
    private Integer sort;
    private String component;
    List<SysOrganDto> children = new ArrayList<>();
}
