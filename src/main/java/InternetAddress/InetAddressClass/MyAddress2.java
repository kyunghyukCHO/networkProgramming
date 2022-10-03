package InternetAddress.InetAddressClass;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress2 {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String dottedQuad = ia.getHostAddress();
            System.out.println("My Address is " + dottedQuad);
        } catch (UnknownHostException ex) {
            System.out.println("I'm sorry. i don't know my own address.");
        }
    }
}
