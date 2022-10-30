package com.example.renwushu.config.minio;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UploadResponse {
    String id;
    String link;
    String fileName;
    Date uploadDate;
    String originalFilename;
    Long size;
    String fileExt;
}
