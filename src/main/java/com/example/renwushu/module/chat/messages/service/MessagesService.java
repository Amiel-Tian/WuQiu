package com.example.renwushu.module.chat.messages.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.chat.messages.entity.Messages;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RedStar
 * @since 2023-02-09
 */
public interface MessagesService extends IService<Messages> {

    LambdaQueryWrapper<Messages> createQueryWrapper(Messages param);
}
