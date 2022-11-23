package URLandURI.URL;

import java.awt.image.ImageProducer;
import java.net.*;
import java.io.*;

public class ContentTypePerformanceTest {
    public static void main(String[] args) {
        try {
            //URL u = new URL("http://cdn.oreillystatic.com/oreilly/images/odot_janna_doga");
            URL u = new URL("http:/home.konkuk.ac.kr/~leehw/Site/nptest/files/logo.png");
            Class<?>[] types = new Class[4];
            types[0] = String.class;
            types[1] = Reader.class;
            types[2] = InputStream.class;
            types[3] = ImageProducer.class;
            Object o = u.getContent(types);

            if (o == null) {
                System.out.println("null object");
            }
            System.out.println("I got a "+o.getClass().getName());
            if (o instanceof String) {
                System.out.println(o);
            } else if (o instanceof Reader) {
                int c;
                Reader r = (Reader) o;
                while((c=r.read())!=-1) System.out.print((char) c);
                r.close();
            } else if (o instanceof InputStream) {
                int c;
                InputStream in = (InputStream) o;
                while((c=in.read())!=-1) System.out.write((char) c);
                in.close();
            } else if (o instanceof ImageProducer) {
                System.out.println("ImageProducer returned");
            } else {
                System.out.println("Eroor: unexpected tpye "+o.getClass());
            }
        } catch (MalformedURLException me) {
            System.err.println(me);
        } catch (IOException ie) {
            System.err.println(ie);
        }
    }
}
