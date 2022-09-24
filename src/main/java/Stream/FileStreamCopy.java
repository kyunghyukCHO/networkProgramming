package Stream;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStreamCopy {
    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.out.println("사용법: java FileView 파일이름1 파일이름2"); // source , destination
//            System.exit(0);
//        }

        // 초기화
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // 입력인자 입력
            fis = new FileInputStream("src/main/java/Stream/Copy.txt");
            fos = new FileOutputStream("src/main/java/Stream/Paste.txt");
            int readCount = 0;
            byte[] buffer = new byte[512];

            // read -> buffer 사이즈만큼 저장 -> write
            while((readCount=fis.read(buffer)) != -1) {  // 읽을 byte가 없을 시 return -1
                //System.out.write(i);
                fos.write(buffer,0,readCount);
            }
            System.out.println("복사가 완료되었습니다.");
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {}
            try {
                fos.close();
            } catch (IOException e) {}
        }
    }
}
