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

#### 5-FtpUtli使用示例

```java
package com.lc.ftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootApplication
public class FtpApplication {
    public static void main(String[] args) {
        SpringApplication.run(FtpApplication.class, args);
    }

    @Resource
    private FtpTemplate ftpTemplate;

    /**
     * 上传图片
     *
     * @return
     */
//    @GetMapping("upload")
    public Boolean upload() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\win10\\Pictures\\test.jpg");
            return ftpTemplate.upload(inputStream, "/t", "tests.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除图片
     *
     * @return
     */
//    @GetMapping("delete")
    public Boolean delete() {
        try {
            return ftpTemplate.delete("/t", "tests.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


```

