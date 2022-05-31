package com.lc.utli;


import com.lc.exception.FtpException;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: FtpUtli
 * @Date: 2021/07/31
 * @Author: lc
 */
public class FtpUtli {

    /**
     * 判断路径是否存在
     *
     * @param ftpClient
     * @param beanPath  根
     * @param path      子
     * @return
     */
    public static boolean isPath(FTPClient ftpClient, String beanPath, String path) {
        try {
            isBaesPath(beanPath);
            isPath(path);
            return ftpClient.changeWorkingDirectory(beanPath + "/" + path);
        } catch (IOException e) {
            throw new FtpException("FtpClients 连接异常:" + e);
        }
    }

    public static boolean mkdirPath(FTPClient ftpClient, String beanPath, String path) {
        try {
            isBaesPath(beanPath);
            isPath(path);
            String paths[] = path.split("/");
            String beanPaths = beanPath;
            for (String dir : paths) {
                if (null != dir || !"".equals(dir)) {
                    beanPaths = beanPaths + "/" + dir;
                    if (!ftpClient.changeWorkingDirectory(beanPaths)) {
                        if (!ftpClient.makeDirectory(beanPaths)) {
                            return false;
                        } else {
                            ftpClient.changeWorkingDirectory(beanPaths);
                        }
                    }

                }
            }
            return ftpClient.changeWorkingDirectory(beanPath + "/" + path);
        } catch (IOException e) {
            throw new FtpException("FtpClients 连接异常:" + e);
        }
    }

    /**
     * 判断子路径是否合法
     *
     * @param path
     */
    public static void isPath(String path) {
        if (null == path || "".equals(path)) {
            throw new FtpException("path参数错误");
        }
        String paths[] = path.split("/");

        if (null == paths || paths.length < 1) {
            throw new FtpException("path参数错误");
        }
        if (paths.length > 10) {
            throw new FtpException("path层级不能超过10层");
        }
    }

    /**
     * 判断根路径是否合法
     *
     * @param baesPath
     */
    public static void isBaesPath(String baesPath) {
        if (null == baesPath || "".equals(baesPath)) {
            throw new FtpException("baesPath参数错误");
        }
    }

    /**
     * 判断fileName是否合法
     *
     * @param fileName
     */
    public static void isFileName(String fileName) {
        if (null == fileName || "".equals(fileName)) {
            throw new FtpException("fileName参数错误");
        }
        if (fileName.contains("\\")) {
            throw new FtpException("参数含有非法字符'\\'");
        }
    }

    /**
     * 判断filePath是否合法
     *
     * @param filePath
     */
    public static void isFilePath(String filePath) {
        if (null == filePath || "".equals(filePath)) {
            throw new FtpException("filePath参数错误");
        }
        if (filePath.contains("\\")) {
            throw new FtpException("参数含有非法字符'\\'");
        }
    }

    /**
     * 获取文件路径及文件名称
     *
     * @param site
     */
    public static String[] getFilePathName(String site) {
        int i = site.lastIndexOf("/");
        return new String[]{site.substring(site.indexOf("/"), i), site.substring(i+1)};
    }

    /**
     * 关闭流
     *
     * @param inputStream
     */
    public static void disconnect(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
