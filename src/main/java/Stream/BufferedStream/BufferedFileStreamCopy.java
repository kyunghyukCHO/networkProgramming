package Stream.BufferedStream;

import java.io.*;

public class BufferedFileStreamCopy {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("사용법: java FileView 파일이름1 파일이름2"); // source , destination
            System.exit(0);
        }

        // 초기화
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // 입력인자 입력
            fis = new FileInputStream(args[0]);
            fos = new FileOutputStream(args[1]);

            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            // 기존 FileCopy : fis 로 부터 buffer 로 바로 가져옴.. buffer 에서 fos 로 바로 write ..
            // BufferedStream : fis 로 부터 메모리의 bis 에 저장한 뒤 buffer에 read !!
            // bis -> buffer : memory 영역의 JUST I/O Operation 뿐임

            int readCount = 0;
            byte[] buffer = new byte[512];

            while((readCount=bis.read(buffer)) != -1) { // bis 에서 read , bis 가 empty 하다면 fis 로 부터 data를 최대한 read
                bos.write(buffer,0,readCount); // bos 로 부터 write
            }
            System.out.println("복사가 완료되었습니다.");
            bis.close();
            bos.close();
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
