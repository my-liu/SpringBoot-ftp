package com.lc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: FtpConfig
 * @Date: 2021/07/31
 * @Author: lc
 */
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "ftp.client")
public class FtpClientProperties {

    /**
     * 文件服务浏览域名
     */
    private String domain;

    /**
     * Ftp连接地址
     */
    private String host;

    /**
     * Ftp连接端口
     */
    private Integer port;

    /**
     * Ftp用户名
     */
    private String loginName;

    /**
     * Ftp密码
     */
    private String password;

    /**
     * Ftp连接超时时间
     */
    private int connectTimeOut = 5000;

    /**
     * Ftp字符编码
     */
    private String encoding = "UTF-8";

    /**
     * Ftp缓冲区大小
     */
    private int bufferSize = 1024;

    /**
     * Ftp传输文件类型 2=二进制
     */
    private int fileType = 2;

    /**
     * Ftp数据传输超时时间 ms
     */
    private int dataTimeout = 1000 * 60;

    /**
     * 是否启用ftp被动模式
     */
    private boolean passiveMode = true;

    /**
     * Ftp服务器存储根路径
     */
    private String basePath;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getDataTimeout() {
        return dataTimeout;
    }

    public void setDataTimeout(int dataTimeout) {
        this.dataTimeout = dataTimeout;
    }

    public boolean isPassiveMode() {
        return passiveMode;
    }

    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
