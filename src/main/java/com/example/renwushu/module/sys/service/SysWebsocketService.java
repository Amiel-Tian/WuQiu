package com.example.renwushu.module.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.sys.entity.SysWebsocket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2023-02-06
 */
public interface SysWebsocketService extends IService<SysWebsocket> {

    LambdaQueryWrapper<SysWebsocket> createQueryWrapper(SysWebsocket param);
}
