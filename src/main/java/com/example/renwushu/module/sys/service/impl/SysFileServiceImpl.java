package com.example.renwushu.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.renwushu.config.minio.MinioProperties;
import com.example.renwushu.config.minio.UploadResponse;
import com.example.renwushu.module.sys.controller.SysFileController;
import com.example.renwushu.module.sys.dao.SysFileMapper;
import com.example.renwushu.module.sys.entity.SysFile;
import com.example.renwushu.module.sys.service.SysFileService;
import com.example.renwushu.utils.IdHelp;
import com.example.renwushu.utils.MinioUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文件共享表 服务实现类
 * </p>
 *
 * @author RedStar
 * @since 2022-08-01
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {
    private final Log log = LogFactory.getLog(SysFileController.class);
    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private MinioUtil minioUtil;
    @Autowired
    private MinioProperties minioProperties;

    public UploadResponse uploadAttachment(MultipartFile file) throws Exception {

        UploadResponse uploadResponse = null;
        uploadResponse = minioUtil.uploadFile(file, minioProperties.getBucketName(), false);
        SysFile sysFile = saveFile(uploadResponse);
        sysFile.setTemporary("1");

        save(sysFile);
        uploadResponse.setId(sysFile.getId());
        return uploadResponse;
    }

    public UploadResponse uploadTemporary(MultipartFile file) throws Exception {

        UploadResponse uploadResponse = null;
        uploadResponse = minioUtil.uploadFile(file, minioProperties.getBucketName(), true);
        SysFile sysFile = saveFile(uploadResponse);
        sysFile.setTemporary("0");

        save(sysFile);
        uploadResponse.setId(sysFile.getId());
        return uploadResponse;
    }

    private SysFile saveFile(UploadResponse uploadResponse) {
        SysFile sysFile = new SysFile();
        sysFile.setId(IdHelp.UUID());
        sysFile.setFileName(uploadResponse.getFileName());
        sysFile.setOriginName(uploadResponse.getOriginalFilename());
        sysFile.setFileSize(uploadResponse.getSize());
        sysFile.setFileExt(uploadResponse.getFileExt());
        sysFile.setFileLink(uploadResponse.getLink());

        return sysFile;
    }

    public List<SysFile> getByIds(String ids){
        return sysFileMapper.getByIds(ids);
    };
}
