package com.example.renwushu.module.chat.messages.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.module.chat.messages.dao.MessagesMapper;
import com.example.renwushu.module.chat.messages.entity.Messages;
import com.example.renwushu.module.chat.messages.service.MessagesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2023-02-09
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements MessagesService {

    @Override
    public LambdaQueryWrapper<Messages> createQueryWrapper(Messages param) {
        LambdaQueryWrapper<Messages> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(param.getSendId()) && StringUtils.isNotBlank(param.getReceiveId())) {
            queryWrapper.and(wrapper -> wrapper.eq(Messages::getSendId, param.getSendId())
                            .eq(Messages::getReceiveId, param.getReceiveId()))
                    .or(wrapper -> wrapper.eq(Messages::getSendId, param.getReceiveId())
                            .eq(Messages::getReceiveId, param.getSendId()));
        }
        queryWrapper.orderByAsc(Messages::getSendDate);
        return queryWrapper;
    }
}
