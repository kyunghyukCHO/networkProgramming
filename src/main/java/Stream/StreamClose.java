package Stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StreamClose {
    public static void main(String[] args) {

        // 1번째 방법 .. Potential Risk 가 존재함 ..
        try {
            OutputStream out1 = new FileOutputStream("numbers.dat");
            // write to the stream ...
            out1.close();
        }
        catch (IOException ex) {
            System.err.println(ex);
        }

        // 2번째 방법.. finally 사용
        OutputStream out2 = null;
        try {
            out2 = new FileOutputStream("/tmp/data.txt");
            // work with the output stream ...
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (out2 != null) {
                try {
                    out2.close();
                } catch (IOException ex) {
                    // ignore
                }
            }
        }

        // 3번째 방법.. try-with-resources 사용
        try (OutputStream out3 = new FileOutputStream("/tmp/data.txt")) {
            // work with the output stream..
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
