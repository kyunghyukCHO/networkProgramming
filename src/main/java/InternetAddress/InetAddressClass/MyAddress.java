package InternetAddress.InetAddressClass;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address); // jogyeonghyeog-ui-MacBookAir.local/218.38.137.28
            System.out.println(address.getHostAddress()); // 218.38.137.28 -> local loop back address
            // loop back address : 자신의 컴퓨터를 스스로 서버로 만들고 그 컴퓨터에서 콜을 보내 응답을 받을 수 있는 것

            byte[] address2 = {107, 23, (byte)216, (byte)196};
            InetAddress ia = InetAddress.getByAddress(address2);
            InetAddress iaNamed = InetAddress.getByAddress("named.com", address2);
            System.out.println(address2);
            System.out.println(ia);
            System.out.println(iaNamed);

        } catch (UnknownHostException ex) {
            System.out.println("Could not find this computer's address");
        }
    }

}
