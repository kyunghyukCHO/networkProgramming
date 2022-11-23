package URLandURI.URL;

import java.io.*;
import java.net.*;

public class SourceViewerSimpleTest {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            URL u = new URL("http://home.konkuk.ac.kr/~leehw/Site/nptest/2021/MidTerM/file%20{c=60}_{d=29}.txt");
            in = u.openStream();
            int c;
            while((c = in.read()) != -1 ) System.out.write(c);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {

            }
        }
    }
}
