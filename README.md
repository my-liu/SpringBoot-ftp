# SpringBoot-FTP

#### 介绍
使用pool2封装的FtpTemplate.yml配置好FtpClientProperties.java、FtpPoolProperties.java即可开箱即用！

#### 软件架构
基于SpringBoot、commons-net、commons-pool2

#### 安装及使用说明看Ftp配置文件


#### Ftp yml示例

```yaml
ftp:
  client:
    host: 127.0.0.1
    login-name: root
    password: 123456
    port: 22
```




#### Ftp使用示例

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
