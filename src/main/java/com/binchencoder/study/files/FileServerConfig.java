package com.binchencoder.study.files;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * File server Configuration.
 *
 * @author chenbin
 */
@Data
@Configuration
public class FileServerConfig {

    /**
     * File server URL
     */
    @Value("${file.server.url:http://fileserver.com}")
    private String url;

    /**
     * File server download path.
     * <p>
     * file server 下载路径
     */
    @Value("${file.server.downloadPath:http://fileserver.com/download}")
    private String downloadPath;

}