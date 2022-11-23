package Stream.FileStream;

import java.io.*;

public class FileViewByteArray {
    public static void main(String[] args) {

        long start = System.currentTimeMillis(); // 시간 측정

        if(args.length != 1) {
            System.out.println("사용법: java FileView 파일이름");
            System.exit(0);
        }

        FileInputStream fis = null; // FileInputStream 선언 및 초기화

        try {
            fis = new FileInputStream(args[0]); // 객체 생 args[0] = 파일이름 , 바로 파일이름을 주어도 됨
            int readcount = 0;
            byte[] buffer = new byte[512]; // buffer 생성
            while((readcount=fis.read(buffer)) != -1) {  // buffer 에 read -> return the number of bytes read
//                System.out.println(readcount);
                System.out.write(buffer,0,readcount); // buffer 의 offset 0 부터 readcount 만큼 wrtie
//                System.out.println("1");

            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {

            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Run-Time: " + (end-start));

    }
}
