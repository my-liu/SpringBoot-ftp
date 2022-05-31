# FtpUtli使用参数配置简介

#### 1-FtpConfig参数

```java
 *host: 				Ftp连接地址IP
 port: 				Ftp连接端口 		默认21
 *userName:			Ftp登录用户名称
 *password:			Ftp登录密码
 connectTimeOut: 	Ftp连接超时时间 	   默认5s					单位:ms
 controlEncoding:	Ftp字符编码		  	默认Utu-8
 bufferSize:		Ftp缓冲区大小	    默认1024 此参数根据你的带宽设置比例
 fileType:			Ftp传输文件类型      默认2=二进制
 dataTimeout:		Ftp数据传输超时时间   默认60s 				单位ms
 passiveMode:		是否启用ftp被动模式   默认true
 *basePath:			Ftp根路径 例子:/home/path (√)  /home/path/ (×) 
 *必填
```

#### 2-FtpPoolConfig

```java

blockWhenExhausted:			连接耗尽时是否阻塞, false报异常,ture阻塞直到超时		  默认true
maxWaitMillis:				获取连接最大阻塞时间ms -1为无限等待					默认5s
maxTotal:					允许创建资源的最大数量  							  默认 16
maxIdle:					最大空闲资源数										 默认值 8
minIdle:					最小空闲资源数										 默认值 2
testOnBorrow:				获取连接时检测是否有效								  默认true
testOnReturn:				归还连接时检测是否有效								  默认false
testOnCreate:				创建连接时检测是否有效								  默认false
testWhileIdle:				是否检测空闲连接是否有效							  默认true
minEvictableIdleTimeMillis: 空闲连接超时时间									默认60M
softMinEvictableIdleTimeMillis:空闲连接超时时间									默认30M
timeBetweenEvictionRunsMillis:空闲对象回收（轮询间隔时间，单位毫秒）-1不启动回收 	  默认10M
numTestsPerEvictionRun:   设置为 小于0时，回收资源的个数为 (int)Math.ceil( 池中空闲资源个数 / Math.abs(numTestsPerEvictionRun) ); 
设置为 大于0时，回收资源的个数为 Math.min( numTestsPerEvictionRun,池中空闲的资源个数 );

```

#### 3-配置要求

```java
jdk			1.8+
pool2 		2.9+
commons-net 3.3+
```

#### 4-SpringBoot配置示例

```java
import com.lc.config.FtpConfig;
import com.lc.config.FtpPoolConfig;
import com.lc.ftp.FtpClientFactory;
import com.lc.ftp.FtpClientPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpAllConfig {

    @Bean
    public FtpConfig ftpConfig() {
        return new FtpConfig();
    }

    @Bean
    public FtpPoolConfig ftpPoolConfig() {
        return new FtpPoolConfig();
    }


    @Bean
    public FtpClientPool ftpClientPool(FtpConfig ftpConfig, FtpPoolConfig ftpPoolConfig) {
        FtpClientPool ftpClientPool=new FtpClientPool(new FtpClientFactory(ftpConfig),ftpPoolConfig,ftpConfig.getBasePath());
        return ftpClientPool;
    }
}

```

#### 5-FtpUtli使用示例

```java
import com.lc.ftp.FtpClientPool;
import com.lc.ftp.FtpClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
public class FtpController {

    @Resource
    private FtpClientPool ftpClientPool;

    /**
     * 上传图片
     * @return
     */
    @GetMapping("upload")
    public Boolean upload() {
        InputStream inputStream= null;
        try {
                inputStream = new FileInputStream("C:\\Users\\win10\\Pictures\\test.jpg");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FtpClients ftpClients= null;
        try {
            ftpClients = ftpClientPool.getFtpClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean success = ftpClients.upload(inputStream, "/t","tests.jpg");
        return success;
    }
    /**
     * 删除图片
     * @return
     */
    @GetMapping("delete")
    public Boolean delete() {
        FtpClients ftpClients= null;
        try {
            ftpClients = ftpClientPool.getFtpClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean success = ftpClients.delete( "/t","tests.jpg");
        return success;
    }
}

```

