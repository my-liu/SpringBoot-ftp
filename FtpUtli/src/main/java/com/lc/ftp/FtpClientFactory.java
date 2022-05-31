package com.lc.ftp;

import com.lc.config.FtpConfig;
import com.lc.exception.FtpPoolException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.IOException;

/**
 * @program: FtpFactory
 * @Date: 2021/08/02
 * @Author: lc
 */
public class FtpClientFactory extends BasePooledObjectFactory<FTPClient> {

    private FtpConfig ftpConfig;

    public FtpClientFactory(FtpConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
    }


    @Override
    public FTPClient create() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(ftpConfig.getConnectTimeOut());
        try {
            ftpClient.connect(ftpConfig.getHost(), ftpConfig.getPort());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new FtpPoolException("FtpServer 拒绝连接");
            }
            boolean result = ftpClient.login(ftpConfig.getUserName(), ftpConfig.getPassword());
            if (!result) {
                throw new FtpPoolException("FtpServer 登录失败");
            }

            ftpClient.setControlEncoding(ftpConfig.getControlEncoding());
            ftpClient.setBufferSize(ftpConfig.getBufferSize());
            ftpClient.setFileType(ftpConfig.getFileType());
            ftpClient.setDataTimeout(ftpConfig.getDataTimeout());
            if (ftpConfig.isPassiveMode()) {
                ftpClient.enterLocalPassiveMode();//进入被动模式
            }
        } catch (IOException e) {
            throw new FtpPoolException("FtpClients 连接异常:" + e);
        }
        return ftpClient;
    }

    @Override
    public PooledObject<FTPClient> wrap(FTPClient ftpClient) {
        return new DefaultPooledObject<FTPClient>(ftpClient);
    }

    @Override
    public void destroyObject(PooledObject<FTPClient> p) throws Exception {
        FTPClient ftpClient = p.getObject();
        ftpClient.logout();
        ftpClient.disconnect();
        super.destroyObject(p);
    }

    @Override
    public boolean validateObject(PooledObject<FTPClient> p) {
        FTPClient ftpClient = p.getObject();
        boolean connect = false;
        try {
            connect = ftpClient.sendNoOp();
        } catch (IOException e) {
            return false;
        }
        return connect;
    }
}
