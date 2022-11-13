package com.example.renwushu.module.renwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.renwushu.module.renwu.entity.RenwuProject;
import com.example.renwushu.module.renwu.dao.RenwuProjectMapper;
import com.example.renwushu.module.renwu.service.RenwuProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-11-13
 */
@Service
public class RenwuProjectServiceImpl extends ServiceImpl<RenwuProjectMapper, RenwuProject> implements RenwuProjectService {

    public LambdaQueryWrapper<RenwuProject> createQueryWrapper(RenwuProject param){
        LambdaQueryWrapper<RenwuProject> queryWrapper = new LambdaQueryWrapper<>();

        return queryWrapper;
    };
}
