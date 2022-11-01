package com.lc.ftp;

import com.lc.ftp.exception.FtpException;
import com.lc.ftp.utli.FtpUtli;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: FtpClients
 * @Date: 2021/07/31
 * @Author: lc
 */
public class FtpTemplate {

    private String basePath;

    private FtpPool<FTPClient> ftpPool;

    public FtpTemplate(String basePath, FtpPool ftpPool) {
        this.basePath = basePath;
        this.ftpPool = ftpPool;
    }

    /**
     * 上传文件
     *
     * @param fileInputStream 文件流
     * @param path            子目录
     * @param fileName        文件名称(带后缀)
     * @return
     */
    public boolean upload(InputStream fileInputStream, String path, String fileName) throws Exception {
        boolean success = false;
        FTPClient ftpClient = null;
        try {
            ftpClient = ftpPool.borrowObject();
            FtpUtli.isFilePath(path);
            FtpUtli.isFileName(fileName);
            if (!FtpUtli.isPath(ftpClient, basePath, path)) {
                if (!FtpUtli.mkdirPath(ftpClient, basePath, path)) {
                    throw new FtpException("创建path失败");
                }
            }
            success = ftpClient.storeFile(fileName, fileInputStream);
        } catch (FtpException e) {
            throw e;
        } catch (IOException e) {
            throw new FtpException("FtpClients connect err:" + e);
        } finally {
            FtpUtli.disconnect(fileInputStream);
            this.close(ftpClient);
        }
        return success;
    }

    /**
     * 删除指定目录的文件
     *
     * @param path     子目录
     * @param fileName 文件名
     * @return
     */
    public boolean delete(String path, String fileName) throws Exception {
        boolean success = false;
        FTPClient ftpClient = null;
        try {
            ftpClient = ftpPool.borrowObject();
            FtpUtli.isFilePath(path);
            FtpUtli.isFileName(fileName);
            if (!FtpUtli.isPath(ftpClient, basePath, path)) {
                throw new FtpException("path is null");
            }
            ftpClient.enterLocalPassiveMode();

            String[] strings = ftpClient.listNames(fileName);
            if (null == strings || strings.length < 1) {
                throw new FtpException("file is null");
            }
            success = ftpClient.deleteFile(basePath + "/" + path + "/" + fileName);

        } catch (FtpException e) {
            throw new FtpException(e.getMessage());
        } catch (IOException e) {
            throw new FtpException("FtpClients 连接异常:" + e.getMessage());
        } finally {
            this.close(ftpClient);
        }
        return success;
    }

    /**
     * 归还连接到pool
     */
    private void close(FTPClient ftpClient) {
        if (ftpClient == null) {
            return;
        }
        ftpPool.returnObject(ftpClient);
    }
}
