package com.example.renwushu.module.renwushu.business.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.renwushu.business.entity.BusinessInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.renwushu.module.renwushu.business.entity.dto.BusinessInfoDto;
import com.example.renwushu.module.sys.entity.SysUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2022-12-09
 */
public interface BusinessInfoService extends IService<BusinessInfo> {

    LambdaQueryWrapper<BusinessInfo> createQueryWrapper(BusinessInfo param);

    List<BusinessInfoDto> getTreeData(SysUser loginUser);
}
