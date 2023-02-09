package com.example.renwushu.module.sys.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysWebSocketDto {
    String type;

    Object data;
}
