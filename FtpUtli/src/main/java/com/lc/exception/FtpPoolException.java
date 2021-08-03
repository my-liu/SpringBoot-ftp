package com.lc.exception;


/**
 * @program: FtpPool err
 * @Date: 2021/08/02
 * @Author: lc
 */
public class FtpPoolException extends Exception{
    private static final long serialVersionUID = 202108021705199819L;


    public FtpPoolException( String msg) {
        super(msg);
    }
}
