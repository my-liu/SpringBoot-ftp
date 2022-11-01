//package com.lc.ftp;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import javax.annotation.Resource;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//@SpringBootApplication
//public class FtpApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(FtpApplication.class, args);
//    }
//
//    @Resource
//    private FtpTemplate ftpTemplate;
//
//    /**
//     * 上传图片
//     *
//     * @return
//     */
////    @GetMapping("upload")
//    public Boolean upload() {
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream("C:\\Users\\win10\\Pictures\\test.jpg");
//            return ftpTemplate.upload(inputStream, "/t", "tests.jpg");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 删除图片
//     *
//     * @return
//     */
////    @GetMapping("delete")
//    public Boolean delete() {
//        try {
//            return ftpTemplate.delete("/t", "tests.jpg");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
