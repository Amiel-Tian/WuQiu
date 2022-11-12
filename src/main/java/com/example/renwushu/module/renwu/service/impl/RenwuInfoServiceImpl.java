package com.example.renwushu.module.renwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.common.QueryField;
import com.example.renwushu.module.renwu.entity.RenwuInfo;
import com.example.renwushu.module.renwu.dao.RenwuInfoMapper;
import com.example.renwushu.module.renwu.service.RenwuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.utils.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-11-03
 */
@Service
public class RenwuInfoServiceImpl extends ServiceImpl<RenwuInfoMapper, RenwuInfo> implements RenwuInfoService {

    @Override
    public LambdaQueryWrapper<RenwuInfo> createQueryWrapper(RenwuInfo param) {
        LambdaQueryWrapper<RenwuInfo> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(param.getCreateBy())){
            queryWrapper.eq(RenwuInfo::getCreateBy, param.getCreateBy());
        }
        if (StringUtils.isNotBlank(param.getMonth())){
            String[] split = param.getMonth().split("-");
            Map<String, String> firstDayAndLastDayOfTheSpecifiedMonth = ToolUtil.getFirstDayAndLastDayOfTheSpecifiedMonth(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
//            queryWrapper.ge(RenwuInfo::getCreateDate,  firstDayAndLastDayOfTheSpecifiedMonth.get("start"));
//            queryWrapper.le(RenwuInfo::getCreateDate,  firstDayAndLastDayOfTheSpecifiedMonth.get("end"));
            queryWrapper.between(RenwuInfo::getCreateDate,firstDayAndLastDayOfTheSpecifiedMonth.get("start"),firstDayAndLastDayOfTheSpecifiedMonth.get("end"));
        }

        if (param.getStatus() != null) {
            queryWrapper.eq(RenwuInfo::getStatus, param.getStatus());
        } else {
            queryWrapper.eq(RenwuInfo::getStatus, QueryField.STATU_NOR);
        }
        if (StringUtils.isNotEmpty(param.getOrderBy())) {
            if (StringUtils.isNotEmpty(param.getOrderByType())
                    && "asc".equals(param.getOrderByType())) {
                queryWrapper.orderByAsc(RenwuInfo::getOrderBy);
            } else {
                queryWrapper.orderByDesc(RenwuInfo::getOrderBy);
            }
        } else {
            queryWrapper.orderByDesc(RenwuInfo::getCreateDate);
        }
        return queryWrapper;
    }
}
