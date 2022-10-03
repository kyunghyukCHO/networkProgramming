package InternetAddress.InetAddressClass;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLOutput;

public class AddressResolutionByName {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.konkuk.ac.kr");
            System.out.println(address);

            InetAddress address2 = InetAddress.getByName("202.30.30.100"); // DNS 컨택을 하지 않습니다.
            System.out.println(address2);
            System.out.println(address2.getHostName()); // reverse name look up 을 통해 DNS 컨택
            // winter.konkuk.ac.kr 이 출력됩니다.
            // 여기서 winter 도 일종의 웹사이트인데 같은 IP를 사용합니다.

            InetAddress[] addresses = InetAddress.getAllByName("www.daum.net");
            for (InetAddress address3 : addresses) {
                System.out.println(address3);
            }

        } catch (UnknownHostException ex) {
            System.out.println("Could not find www.konkuk.ac.kr");
        }
    }
}
