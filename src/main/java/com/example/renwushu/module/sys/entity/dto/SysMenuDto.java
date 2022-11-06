package com.example.renwushu.module.sys.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysMenuDto implements Serializable {
    private String id;
    private String name;
    private String icon;
    private String path;
    private String perms;
    private Integer type;
    private Integer sort;
    private String component;
    List<SysMenuDto> children = new ArrayList<>();
}
