package Stream.CharacterStream;

import java.io.FileReader;
import java.io.FileWriter;

// FileReader FileWriter : NOT Recommended .. USE InputStreamReader + FileInputStream .. !!

public class FileReaderWriter {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("사용법: java FileCopy 파일이름1 파일이름2"); // source , destination
            System.exit(0);
        }

        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(args[0]);
            fw = new FileWriter(args[1]);
            char[] buffer = new char[512];
            int readCount = 0;
            while ((readCount = fr.read(buffer)) != -1) {
                fw.write(buffer, 0, readCount);
            }
            System.out.println("파일을 복사하였습니다.");
            fr.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }



    }
}
