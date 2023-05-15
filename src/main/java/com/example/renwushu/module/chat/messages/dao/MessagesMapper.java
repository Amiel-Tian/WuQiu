package com.example.renwushu.module.chat.messages.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.renwushu.module.chat.messages.entity.Messages;
import com.example.renwushu.module.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author RedStar
 * @since 2023-02-09
 */
@Mapper
public interface MessagesMapper extends BaseMapper<Messages> {

    List<SysUser> userRecord(String id);
}
