package HTTP;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EncodingAwareSourceViewer {
    public static void main(String[] args) {
        try {
            String encoding = "ISO-8859-1";
            URL u = new URL("http://ecampus.konkuk.ac.kr");
            URLConnection uc = u.openConnection();
            String contentType = uc.getContentType(); // 실제 Connection 위치 !!
            int encodingStart = contentType.indexOf("charset=");
            if (encodingStart != -1) {
                encoding = contentType.substring(encodingStart + 8);
            }
            System.out.println(contentType);
            System.out.println(encoding);
            InputStream in = new BufferedInputStream(uc.getInputStream());
            Reader r = new InputStreamReader(in, encoding);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
            r.close();
        } catch (MalformedURLException ex) {
            System.err.println(args[0] + " is not a perseable URL");
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Server sent an encoding Java does not support: " + ex.getMessage());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
