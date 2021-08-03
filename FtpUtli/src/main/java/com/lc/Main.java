//package com.lc;
//
//import com.lc.ftp.FtpClients;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//public class Main {
//    public static void main(String ager[]) throws FileNotFoundException {
////        FtpClients ftpClients=new FtpClients();
//        add();
////        delete();
//    }
//
//    static void add() throws FileNotFoundException {
//        InputStream inputStream=new FileInputStream("C:\\Users\\win10\\Pictures\\test.jpg");
//        FtpClients ftpClients=new FtpClients();
//        boolean delete = ftpClients.upload(inputStream, "/t","tests.jpg");
//        System.out.println(delete);
//    }
//    static void delete(){
//        FtpClients ftpClients=new FtpClients();
//        boolean delete = ftpClients.delete("t", "tests.jpg");
//        System.out.println(delete);
//    }
//}
