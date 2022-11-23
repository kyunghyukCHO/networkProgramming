package Stream.InputStream;

import java.io.*;

public class ReadCharacterSingleByte {
    public static void main(String[] args) {
        InputStream in = System.in;
        OutputStream out = System.out;

        try {
            int input = in.read(); // 1 byte read. 예를들어 abc 중 a 를 read
            System.out.println(input);
            out.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}

// << 17 입력 시 49 , 1 이 출력되는 이유 >>
// 17의 1 byte 입력이므로 1 이 입력됩니다. 1 에 대한 아스키코드는 49
// write 시 49번에 헤당하는 아스키 문자는 1 입니다.




