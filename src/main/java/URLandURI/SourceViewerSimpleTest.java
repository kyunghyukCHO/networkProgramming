package URLandURI;

import java.io.*;
import java.net.*;

public class SourceViewerSimpleTest {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            URL u = new URL("http://www.lolcats.com");
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
