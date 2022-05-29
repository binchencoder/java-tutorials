package com.binchencoder.study.files;

import com.sun.istack.internal.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件下载工具类
 *
 * @author chenbin
 */
@Component
@Slf4j
public class DownloadFileUtils {

    @Autowired
    private FileServerConfig fileServerConfig;

    final TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        }
    };

    final HostnameVerifier verifiedAllHostname = (hostname, session) -> true;

    private PoolingHttpClientConnectionManager cm;
    private SSLConnectionSocketFactory sslSocketFactory;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException, KeyManagementException {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(10);
        cm.setDefaultMaxPerRoute(10);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
            verifiedAllHostname);
    }

    /**
     * 下载指定net url的文件, 带token下载
     *
     * @param targetPathname 目标文件路径
     * @param fileNetUrl     文件net url
     * @param token          用户认证token
     */
    public void downloadFile(String targetPathname, String fileNetUrl, String token)
        throws FileNotFoundException {
        String downloadUrl = this.getDownloadFsUrl(fileNetUrl, token);

        File targetFile = new File(targetPathname);
        this.downloadFile(targetFile, downloadUrl);
    }

    /**
     * 下载指定net url的文件, 带token下载
     *
     * @param targetFile 目标文件
     * @param fileNetUrl 文件net url
     * @param token      用户认证token
     * @throws FileNotFoundException
     */
    public void downloadFile(File targetFile, String fileNetUrl, String token)
        throws FileNotFoundException {
        String downloadUrl = this.getDownloadFsUrl(fileNetUrl, token);
        this.downloadFile(targetFile, downloadUrl);
    }

    /**
     * 下载指定net url的文件, 返回文件流
     *
     * @param fileUrl 文件net url
     */
    public InputStream getFileInputStream(String fileUrl) throws FileNotFoundException {
        log.info("Download file url:{}", fileUrl);
        HttpGet get = new HttpGet(fileUrl);
        CloseableHttpClient httpClient = this.getHttpClient();

        InputStream is = null;
        try {
            CloseableHttpResponse resp = httpClient.execute(get);
            HttpEntity entity = resp.getEntity();
            int statusCode = resp.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode) {
                String error = EntityUtils.toString(entity);
                if (HttpStatus.SC_NOT_FOUND == statusCode) {
                    throw new FileNotFoundException(error);
                }

                EntityUtils.consume(entity);
                log.error("Failed download file, httpStatusCode:{}, fileURL:{}, error{}",
                    statusCode, fileUrl, error);
                // TODO(chenbin) 文件下载失败处理
                return null;
            }

            is = entity.getContent();
            get.reset();
        } catch (IOException e) {
            log.error("Download file IOException, fileURL:{}", fileUrl, e);
            get.abort();
        } finally {
            if (Objects.nonNull(is)) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.warn("Close input stream", e);
                }
            }
        }
        return is;
    }

    /**
     * 下载指定net url的文件
     *
     * @param targetFile 目标文件
     * @param fileNetUrl 文件net url
     */
    public void downloadFile(File targetFile, String fileNetUrl) throws FileNotFoundException {
//        HttpPost post = new HttpPost(fileUrl);
        log.info("Download file url:{}, target file path:{}", fileNetUrl, targetFile.getPath());
        HttpGet get = new HttpGet(fileNetUrl);
        CloseableHttpClient httpClient = this.getHttpClient();

        InputStream is = null;
        OutputStream os = null;
        try {
            CloseableHttpResponse resp = httpClient.execute(get);
            HttpEntity entity = resp.getEntity();
            int statusCode = resp.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode) {
                String error = EntityUtils.toString(entity);
                if (HttpStatus.SC_NOT_FOUND == statusCode) {
                    throw new FileNotFoundException(error);
                }

                EntityUtils.consume(entity);
                log.error("Failed download file, httpStatusCode:{}, fileURL:{}, error{}",
                    statusCode, fileNetUrl, error);
                // TODO(chenbin) 文件下载失败处理
                return;
            }

            File parentFile = targetFile.getParentFile();
            FileUtils.mkdirsAddChmod777(parentFile);

            is = entity.getContent();
            os = new FileOutputStream(targetFile);
            entity.writeTo(os);
            get.reset();
        } catch (IOException e) {
            log.error("Download file IOException, fileURL:{}", fileNetUrl, e);
            get.abort();
        } finally {
            if (Objects.nonNull(is)) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.warn("Close input stream", e);
                }
            }

            if (Objects.nonNull(os)) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.warn("Close output stream", e);
                }
            }
        }
    }

    private CloseableHttpClient getHttpClient() {
        CloseableHttpClient client = HttpClients.custom()
            .setSSLSocketFactory(sslSocketFactory)
//            .setSSLHostnameVerifier(verifiedAllHostname)
            .setDefaultRequestConfig(RequestConfig.custom()
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build())
            .setConnectionManager(cm).build();

        return client;
    }

    @NotNull
    private String getDownloadFsUrl(String fileUrl, String token) {
        String fileId = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        StringBuffer sb = new StringBuffer(fileServerConfig.getUrl()).append(
            fileServerConfig.getDownloadPath());
        return MessageFormat.format(sb.toString(), fileId, token);
    }

}
