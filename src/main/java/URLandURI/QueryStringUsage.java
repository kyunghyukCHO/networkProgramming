package URLandURI;

import java.io.*;
import java.net.*;

public class QueryStringUsage {
    public static void main(String[] args) {
        QueryString qs = new QueryString();
        qs.add("h1", "en");
        qs.add("as_q", "Java");
        qs.add("as_epq", "I/O");

        String url = "http://www.google.com/search?" + qs;
        System.out.println(url);

        InputStream in = null;
        try {
//            String output = URLDecoder.decode(url, "UTF-8");
//            System.out.println(output);

            URL u = new URL(url);
//            URL u = new URL("https://www.google.com/search?q=java+i%2Fo");
            in = u.openStream();
            int c;
            while ((c = in.read()) != -1) { // Server returned HTTP response 403 for URL --> 403 forbidden
                System.out.write(c);
            }
        } catch (UnsupportedEncodingException ex) {

        } catch (IOException ex) {

        }
    }
}
