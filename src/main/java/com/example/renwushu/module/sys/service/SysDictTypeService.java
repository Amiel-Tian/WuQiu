package com.example.renwushu.module.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.sys.entity.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.renwushu.module.sys.entity.dto.SysDictDto;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author RedStar
 * @since 2022-11-10
 */
public interface SysDictTypeService extends IService<SysDictType> {

    LambdaQueryWrapper<SysDictType> createQueryWrapper(SysDictType param);

    List<SysDictDto> getTreeDict();
}
