package Stream;

import java.io.*;

public class FileViewSingleByte {
    public static void main(String[] args) {

        long start = System.currentTimeMillis(); // 시간 측정

        if(args.length != 1) {
            System.out.println("사용법: java FileView 파일이름");
            System.exit(0);
        }

        FileInputStream fis = null; // FileInputStream 선언 및 초기화

        try {
            fis = new FileInputStream(args[0]); // 객체 생 args[0] = 파일이름 , 바로 파일이름을 주어도 됨
            int i = 0;
            while((i=fis.read()) != -1) { // 1byte read 후 return
                System.out.write(i); // return 된 값을 EOF 까지 write
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
