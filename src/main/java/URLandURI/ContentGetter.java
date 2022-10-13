package URLandURI;

import java.net.*;
import java.io.*;

public class ContentGetter {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.oreilly.com");
            //URL u = new URL("http:/home.konkuk.ac.kr/~leehw/Site/nptest/files/logo.png");
            Object o = u.getContent();
//            System.out.println(o.getClass().getName());
            int c;
            InputStream r = (InputStream) o;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            r.close();
            System.out.println("I got a "+o.getClass().getName()); // 클래스 이름을 출력해줍니다.
        } catch (MalformedURLException ex) {
            System.err.println(args[0] + " is not a parseable URL");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
