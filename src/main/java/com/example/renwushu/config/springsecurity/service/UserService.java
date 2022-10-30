package com.example.renwushu.config.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.entity.SysUserRole;
import com.example.renwushu.module.sys.service.SysUserRoleService;
import com.example.renwushu.module.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)){
            throw new UsernameNotFoundException("用户名不能为空");
        }

        try {
            SysUser user = sysUserService.getByUser(new SysUser()
                    .setUsername(username));

            if (user == null){
                return null;
            }
            //获取用户权限信息，如果没有用户信息直接返回
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(sysUserService.getAuthorityByUser(user.getId()));

            UserDetails userDetails = new User(user.getUsername(),user.getPassword(),grantedAuthorities);
            return userDetails;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
