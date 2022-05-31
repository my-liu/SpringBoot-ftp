package com.lc.ftp;

import com.lc.config.FtpPoolConfig;
import com.lc.exception.FtpException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * @program: Ftp池
 * @Date: 2021/08/02
 * @Author: lc
 */
public class FtpClientPool {

    private GenericObjectPool<FTPClient> pool;

    private String basePath;


    public FtpClientPool(FtpClientFactory ftpClientFactory, FtpPoolConfig ftpPoolConfig, String basePath) {
        this.basePath = basePath;
        this.pool = new GenericObjectPool<FTPClient>(ftpClientFactory, ftpPoolConfig);
    }


    public FtpClients getFtpClients() {
        try {
            return new FtpClients(this.basePath, pool.borrowObject(), this);
        } catch (Exception e) {
            throw new FtpException("获取Ftp连接异常：" + e);
        }
    }

    public void returnObject(FTPClient ftpClient) {
        pool.returnObject(ftpClient);
    }

}
