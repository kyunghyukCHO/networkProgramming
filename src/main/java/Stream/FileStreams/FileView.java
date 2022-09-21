package Stream.FileStreams;

import java.io.*;

public class FileView {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        if(args.length != 1) {
            System.out.println("사용법: java FileView 파일이름");
            System.exit(0);
        }
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(args[0]);
            int readcount = 0;
            byte[] buffer = new byte[512];
            while((readcount=fis.read(buffer)) != -1) {
                System.out.write(buffer,0,readcount);
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
