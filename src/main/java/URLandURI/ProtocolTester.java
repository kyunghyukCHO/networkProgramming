package URLandURI;

import java.net.*;

public class ProtocolTester {

    public static void main(String[] args) {
        testProtocol("http://www.abc.org");
        // etc... 네트워크 챕터5 참고
    }

    public static void testProtocol(String url) {
        try {
            URL u = new URL(url);
            System.out.println(u.getProtocol() + " is supported");
        } catch (MalformedURLException ex) {
            String protocol = url.substring(0, url.indexOf(':'));
            System.out.println(protocol + " is not supported");
        }
    }
}
