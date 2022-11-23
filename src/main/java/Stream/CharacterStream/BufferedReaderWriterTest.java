package Stream.CharacterStream;

import java.io.*;

public class BufferedReaderWriterTest {
    public static void main(String[] args) {

    }

    public static String getMacCyrillicString01(InputStream in) throws IOException {
        Reader r = new InputStreamReader(in, "MacCyrillic");
        r = new BufferedReader(r, 1024);
        StringBuilder sb = new StringBuilder();
        int c;
        while((c=r.read()) != -1) sb.append((char) c);
        return sb.toString();
    }

    // Compare with ..
    // buffer 가 없기 때문에 I/O device 에서 직접 read 해옵니다.
    // 효율성에서 문제가 발생합니다!
    public static String getMacCyrillicString02(InputStream in) throws IOException {
        InputStreamReader r = new InputStreamReader(in, "MacCyrillic");
        StringBuilder sb = new StringBuilder();
        int c;
        while((c=r.read()) != -1) sb.append((char) c);
        return sb.toString();
    }
}
