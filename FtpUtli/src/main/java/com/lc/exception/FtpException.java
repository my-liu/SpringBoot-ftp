package com.lc.exception;

/**
 * @program: Ftp err  RunErr
 * @Date: 2021/07/31
 * @Author: lc
 */
public class FtpException extends RuntimeException {

    private static final long serialVersionUID = 202107311705199819L;


    public FtpException(String msg) {
        super(msg);
    }
}
