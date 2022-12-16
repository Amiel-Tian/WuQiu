package com.example.renwushu.module.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.renwushu.module.sys.entity.SysFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文件共享表 Mapper 接口
 * </p>
 *
 * @author RedStar
 * @since 2022-08-01
 */
@Mapper
public interface SysFileMapper extends BaseMapper<SysFile> {

    List<SysFile> getByIds(String ids);
}
