package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.module.sys.dao.SysWebsocketMapper;
import com.example.renwushu.module.sys.entity.SysWebsocket;
import com.example.renwushu.module.sys.service.SysWebsocketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2023-02-06
 */
@Service
public class SysWebsocketServiceImpl extends ServiceImpl<SysWebsocketMapper, SysWebsocket> implements SysWebsocketService {

    @Override
    public LambdaQueryWrapper<SysWebsocket> createQueryWrapper(SysWebsocket param) {
        LambdaQueryWrapper<SysWebsocket> queryWrapper = new LambdaQueryWrapper<>();


        if (StringUtils.isNotBlank(param.getSendId()) && StringUtils.isNotBlank(param.getReceiveId())) {
            queryWrapper.and(wrapper -> wrapper.eq(SysWebsocket::getSendId, param.getSendId())
                            .eq(SysWebsocket::getReceiveId, param.getReceiveId()))
                    .or(wrapper -> wrapper.eq(SysWebsocket::getSendId, param.getReceiveId())
                            .eq(SysWebsocket::getReceiveId, param.getSendId()));
        }
        queryWrapper.orderByAsc(SysWebsocket::getSendDate);
        return queryWrapper;
    }
}
