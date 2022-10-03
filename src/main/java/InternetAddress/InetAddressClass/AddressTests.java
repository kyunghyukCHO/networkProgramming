package InternetAddress.InetAddressClass;

import java.net.*;

public class AddressTests {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String dottedQuad = ia.getHostAddress();
            System.out.println("My Address is " + dottedQuad);

            System.out.println("This is IPv" + getVersion(ia));
        } catch (UnknownHostException ex) {
            System.out.println("I'm sorry. i don't know my own address.");
        }
    }

    public static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) return 4;
        else if (address.length == 16) return 6;
        else return -1;
    }
}
