// -- 문제 --
// write 할 시 byte 를 출력하는데 왜 문자열이 그대로 출력되는가 ?? -> 출력 인코딩 포맷 설정. 단, 디폴트 인코딩 포맷 존재

package Stream.CharacterStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StreamReaderWriterTest02 {
    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.out.println("사용법: java StreamReaderTest 파일명");
//            System.exit(0);
//        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;

        try {
            //Working Example (English)
//            fis = new FileInputStream(args[0]);
//            isr = new InputStreamReader(fis);
//            osw = new OutputStreamWriter(System.out);

            // Encoding 포맷을 잘 정해줘야 함.
            // Working Example (Korean) : text3.txt created in Windows notepad
//            fis = new FileInputStream("src/main/java/Stream/test.txt");
//            isr = new InputStreamReader(fis,"EUC-KR"); // EUC-KR 로 read
//            osw = new OutputStreamWriter(System.out,"UTF-8"); // UTF-8 로 write

            // Working Example (ENG+Korean) : text5.txt created in MAC TextEdit (saved as plain txt; encoding automatic)
            File file =  new File("src/main/java/Stream/test.txt");
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis,"UTF-8");
            osw = new OutputStreamWriter(System.out, "UTF-8");

            // 인코딩 포맷팅 오류
            // Failing Example (Korean+English)
//            fis = new FileInputStream("text4.rtf");
//            isr = new InputStreamReader(fis,"EUC-KR");
//            osw = new OutputStreamWriter(System.out);

            char[] buffer = new char[512];
            int readCount = 0;
            while ((readCount = isr.read(buffer)) != -1) {
                osw.write(buffer, 0, readCount);
            }

            fis.close();
            isr.close();
            osw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
