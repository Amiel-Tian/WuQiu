package com.example.renwushu.common.base;

import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public class BaseController {
    @Autowired
    HttpServletRequest req;
}
