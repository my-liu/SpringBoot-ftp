package com.lc.ftp;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class FtpPool<FTPClient> extends GenericObjectPool<FTPClient> {
    public FtpPool(PooledObjectFactory<FTPClient> factory) {
        super(factory);
    }

    public FtpPool(PooledObjectFactory<FTPClient> factory, GenericObjectPoolConfig<FTPClient> config) {
        super(factory, config);
    }

    public FtpPool(PooledObjectFactory<FTPClient> factory, GenericObjectPoolConfig<FTPClient> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
