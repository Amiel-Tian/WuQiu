package com.example.renwushu.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.renwushu.config.minio.UploadResponse;
import com.example.renwushu.module.sys.entity.SysFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文件共享表 服务类
 * </p>
 *
 * @author RedStar
 * @since 2022-08-01
 */
public interface SysFileService extends IService<SysFile> {
    UploadResponse uploadAttachment(MultipartFile file) throws Exception;
    UploadResponse uploadTemporary(MultipartFile file) throws Exception;

    List<SysFile> getByIds(String ids);
}
