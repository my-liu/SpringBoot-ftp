package com.lc.ftp.config;

import com.lc.ftp.FtpClientFactory;
import com.lc.ftp.FtpPool;
import com.lc.ftp.FtpTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties({FtpClientProperties.class, FtpPoolProperties.class})
public class FtpAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = "ftpClientFactory")
    public FtpClientFactory redisTemplate(FtpClientProperties ftpClientProperties, FtpPoolProperties poolProperties) {
        return new FtpClientFactory(ftpClientProperties);
    }

    @Bean
    @ConditionalOnMissingBean(name = "ftpClientPoolFactory")
    public FtpPool redisTemplate(FtpClientFactory ftpClientFactory, FtpPoolProperties poolProperties) {
        return new FtpPool(ftpClientFactory, poolProperties);
    }

    @Bean
    @ConditionalOnMissingBean(name = "ftpTemplate")
    public FtpTemplate stringRedisTemplate(FtpClientProperties ftpClientProperties, FtpPool ftpPool) {
        return new FtpTemplate(ftpClientProperties.getBasePath(), ftpPool);
    }
}
