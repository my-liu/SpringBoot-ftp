package com.lc.ftp;

import com.lc.config.FtpClientProperties;
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

    private FtpClientProperties ftpClientProperties;

    public FtpClientFactory(FtpClientProperties ftpClientProperties) {
        this.ftpClientProperties = ftpClientProperties;
    }


    @Override
    public FTPClient create() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(ftpClientProperties.getConnectTimeOut());
        try {
            ftpClient.connect(ftpClientProperties.getHost(), ftpClientProperties.getPort());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new FtpPoolException("FtpServer Connection refused");
            }
            boolean result = ftpClient.login(ftpClientProperties.getLoginName(), ftpClientProperties.getPassword());
            if (!result) {
                throw new FtpPoolException("FtpServer login failure");
            }

            ftpClient.setControlEncoding(ftpClientProperties.getEncoding());
            ftpClient.setBufferSize(ftpClientProperties.getBufferSize());
            ftpClient.setFileType(ftpClientProperties.getFileType());
            ftpClient.setDataTimeout(ftpClientProperties.getDataTimeout());
            if (ftpClientProperties.isPassiveMode()) {
                ftpClient.enterLocalPassiveMode();//进入被动模式
            }
        } catch (IOException e) {
            throw new FtpPoolException("FtpClients err:" + e);
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
