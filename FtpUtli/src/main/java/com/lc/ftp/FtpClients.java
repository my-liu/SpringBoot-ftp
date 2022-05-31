package com.lc.ftp;

import com.lc.exception.FtpException;
import com.lc.utli.FtpUtli;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: FtpClients
 * @Date: 2021/07/31
 * @Author: lc
 */
public class FtpClients {

    private String basePath;

    private FtpClientPool ftpClientPool;

    private FTPClient ftpClient;

    public FtpClients(String basePath, FTPClient ftpClient, FtpClientPool ftpClientPool) {
        this.basePath = basePath;
        this.ftpClient = ftpClient;
        this.ftpClientPool = ftpClientPool;
    }

    /**
     * 上传文件
     *
     * @param fileInputStream 文件流
     * @param path            子目录
     * @param fileName        文件名称(带后缀)
     * @return
     */
    public boolean upload(InputStream fileInputStream, String path, String fileName) {
        boolean success = false;
        try {
            FtpUtli.isFilePath(path);
            FtpUtli.isFileName(fileName);
            if (!FtpUtli.isPath(ftpClient, basePath, path)) {
                if (!FtpUtli.mkdirPath(ftpClient, basePath, path)) {
                    throw new FtpException("创建path失败");
                }
            }
            success = ftpClient.storeFile(fileName, fileInputStream);
        } catch (FtpException e) {
            throw new FtpException(e.getMessage());
        } catch (IOException e) {
            throw new FtpException("FtpClients 连接异常:" + e.getMessage());
        } finally {
            FtpUtli.disconnect(fileInputStream);
            this.close();
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
    public boolean delete(String path, String fileName) {
        boolean success = false;
        try {
            FtpUtli.isFilePath(path);
            FtpUtli.isFileName(fileName);
            if (!FtpUtli.isPath(ftpClient, basePath, path)) {
                throw new FtpException("path不存在");
            }
            ftpClient.enterLocalPassiveMode();

            String[] strings = ftpClient.listNames(fileName);
            if (null == strings || strings.length < 1) {
                throw new FtpException("file不存在");
            }
            success = ftpClient.deleteFile(basePath + "/" + path + "/" + fileName);

        } catch (FtpException e) {
            throw new FtpException(e.getMessage());
        } catch (IOException e) {
            throw new FtpException("FtpClients 连接异常:" + e.getMessage());
        } finally {
            this.close();
        }
        return success;
    }

    /**
     * 归还连接到pool
     */
    private void close() {
            ftpClientPool.returnObject(this.ftpClient);
    }
}
