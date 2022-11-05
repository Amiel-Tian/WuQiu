package com.example.renwushu.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {
    @TableField(exist = false)
    private String orderByType;
    @TableField(exist = false)
    private String orderBy;
    @TableField(exist = false)
    int pageNum;
    @TableField(exist = false)
    int pageSize;
}