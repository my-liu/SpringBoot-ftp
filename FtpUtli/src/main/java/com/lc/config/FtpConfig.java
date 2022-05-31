package com.lc.config;

/**
 * @program: FtpConfig
 * @Date: 2021/07/31
 * @Author: lc
 */
public class FtpConfig {

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 文件服务浏览域名
     */
    private String domain;
    /**
     * Ftp连接地址
     */
    private String host="34.125.19.134";

    /**
     * Ftp连接端口
     */
    private Integer port = 21;

    /**
     * Ftp用户名
     */
    private String userName="ftpuser";

    /**
     * Ftp密码
     */
    private String password="biteyun123.";

    /**
     * Ftp连接超时时间
     */
    private int connectTimeOut = 5000;

    /**
     * Ftp字符编码
     */
    private String controlEncoding = "UTF-8";

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
     * Ftp根路径 *
     */
    private String basePath="/home/ftpuser";

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getControlEncoding() {
        return controlEncoding;
    }

    public void setControlEncoding(String controlEncoding) {
        this.controlEncoding = controlEncoding;
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

    @Override
    public String toString() {
        return "FtpConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", connectTimeOut=" + connectTimeOut +
                ", controlEncoding='" + controlEncoding + '\'' +
                ", bufferSize=" + bufferSize +
                ", fileType=" + fileType +
                ", dataTimeout=" + dataTimeout +
                ", passiveMode=" + passiveMode +
                ", basePath='" + basePath + '\'' +
                '}';
    }
}
