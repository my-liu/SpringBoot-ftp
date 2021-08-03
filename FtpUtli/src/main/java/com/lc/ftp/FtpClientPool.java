package com.lc.ftp;

import com.lc.config.FtpConfig;
import com.lc.config.FtpPoolConfig;
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


    public FtpClients getFtpClients() throws Exception {
        return new FtpClients(this.basePath, pool.borrowObject(), this);
    }

    public void returnObject(FTPClient ftpClient) {
        pool.returnObject(ftpClient);
    }

}
