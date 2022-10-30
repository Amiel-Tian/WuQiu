package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.renwushu.module.sys.entity.SysMenu;
import com.example.renwushu.module.sys.entity.SysRole;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.entity.SysUserRole;
import com.example.renwushu.module.sys.dao.SysUserRoleMapper;
import com.example.renwushu.module.sys.service.SysRoleService;
import com.example.renwushu.module.sys.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-10-30
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
