package com.lc.ftp.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @program: FtpPoolConfig
 * @Date: 2021/08/02
 * @Author: lc
 */
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "ftp.pool")
public class FtpPoolProperties extends GenericObjectPoolConfig {
    /**
     * 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时
     */
    private boolean blockWhenExhausted = true;

    /**
     * 阻塞时间ms
     */
    private long maxWaitMillis = 5000L;

    /**
     *最大连接数
     */
    private int maxTotal = 16;

    /**
     *最大空闲连接数
     */
    private int maxIdle = 8;

    /**
     * 最小空闲连接数
     */
    private int minIdle = 2;

    /**
     * 获取连接时检测是否有效
     */
    private boolean testOnBorrow =true;

    /**
     * 归还连接时检测是否有效
     */
    private boolean testOnReturn=false;

    /**
     * 创建连接时检测是否有效
     */
    private boolean testOnCreate = false;

    /**
     * 是否检测空闲连接是否有效
     */
    private boolean testWhileIdle = true;

    /**
     * 空闲连接超时时间
     */
    private long minEvictableIdleTimeMillis = 1000*60*60;

    /**
     * 空闲连接超时时间(连接大于minIdle才生效)
     */
    private long softMinEvictableIdleTimeMillis = 1000*60*30;

    /**
     * 空闲对象回收（轮询间隔时间，单位毫秒）<1不启动回收
     */
    private long timeBetweenEvictionRunsMillis = 1000*60*10;

    /**
     * 单词空闲对象回收检测连接基数 x/v
     */
    private int numTestsPerEvictionRun = 3;

    public boolean getBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    @Override
    public void setBlockWhenExhausted(boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    @Override
    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    @Override
    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    @Override
    public int getMaxTotal() {
        return maxTotal;
    }

    @Override
    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    @Override
    public int getMaxIdle() {
        return maxIdle;
    }

    @Override
    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    @Override
    public int getMinIdle() {
        return minIdle;
    }

    @Override
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    @Override
    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean getTestOnReturn() {
        return testOnReturn;
    }

    @Override
    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean getTestOnCreate() {
        return testOnCreate;
    }

    @Override
    public void setTestOnCreate(boolean testOnCreate) {
        this.testOnCreate = testOnCreate;
    }

    public boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    @Override
    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    @Override
    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    @Override
    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    @Override
    public long getSoftMinEvictableIdleTimeMillis() {
        return softMinEvictableIdleTimeMillis;
    }

    @Override
    public void setSoftMinEvictableIdleTimeMillis(long softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    @Override
    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    @Override
    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    @Override
    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    @Override
    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }
}
