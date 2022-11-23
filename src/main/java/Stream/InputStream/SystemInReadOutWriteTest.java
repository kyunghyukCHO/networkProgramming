package Stream.InputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SystemInReadOutWriteTest {
    public static void main(String[] args) {
        InputStream in = System.in;
        OutputStream out = System.out;

        try {
            int input = in.read(); // read()는 기본적으로 문자열을 받습니다. 17 입력 시 문자 1 이 입력되고
            System.out.println(input); // 문자 1 을 int 형으로 바꾸면서 ASCII 코드가 입력 됩니다.
            out.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
