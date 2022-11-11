package com.example.renwushu.module.sys.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SysDictDto implements Serializable {
    private String id;
    private String name;
    private String value;
    private Integer sort;
    private String remarks;
    List<SysDictDto> children = new ArrayList<>();
}
