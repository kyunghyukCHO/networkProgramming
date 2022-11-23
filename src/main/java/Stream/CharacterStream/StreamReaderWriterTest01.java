package Stream.CharacterStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamReaderWriterTest01 {
    public static void main(String[] args) {
        try {
            getMacCyrillicString(System.in);
        } catch (IOException ex){

        }
    }

    public static String getMacCyrillicString(InputStream in) throws IOException {
        InputStreamReader r = new InputStreamReader(in, "MacCyrillic");
        StringBuilder sb = new StringBuilder();
        int c;
        while((c=r.read()) != -1) sb.append((char) c);
        return sb.toString();
    }
}
