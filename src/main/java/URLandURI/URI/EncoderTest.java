package URLandURI.URI;

import java.net.*;
import java.io.*;

public class EncoderTest {
    public static void main(String[] args) {
        // Chapter05 40페이지 확인 .. 그 중 한개 예시
        try {
            System.out.println(URLEncoder.encode("This String has spaces", "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }
}
