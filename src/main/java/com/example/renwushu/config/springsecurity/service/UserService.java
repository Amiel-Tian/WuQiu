package com.example.renwushu.config.springsecurity.service;

import com.example.renwushu.common.json.StatusCode;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysUserService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)){
            throw new UsernameNotFoundException("用户名不能为空");
        }

        try {
            SysUser user = sysUserService.getByUser(new SysUser()
                    .setLoginname(username));

            if (user == null){
                throw new JwtException(StatusCode.USER_LOGIN_NAME_FAIL.statusDesc);
            }
            //获取用户权限信息，如果没有用户信息直接返回
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(sysUserService.getAuthorityByUser(user.getId()));

            UserDetails userDetails = new User(user.getLoginname(),user.getPassword(),grantedAuthorities);
            return userDetails;

        }catch (Exception e){
            e.printStackTrace();
            throw new JwtException(StatusCode.USER_LOGIN_NAME_FAIL.statusDesc);
        }
    }
}
