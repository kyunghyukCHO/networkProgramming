package FinalExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class FinalExam201914184 {

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
    }
}


class Client {
    private String ip = "203.252.148.148";
    private int port = 2022;

    private SocketChannel client = null;
    private final int MAX_BUF_SIZE = 65507;

    public Client() {}

    public void connect() {
        try {
            SocketAddress address = new InetSocketAddress(ip, port);

            client = SocketChannel.open();
            client.connect(address);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String theLine = userInput.readLine();

            ByteBuffer output = ByteBuffer.allocate(MAX_BUF_SIZE);

            for (int i = 0; i < theLine.length(); i++) {
                char ch = theLine.charAt(i);
                output.putChar(ch);
            }

            output.flip();
            client.write(output);

            ByteBuffer input = ByteBuffer.allocate(MAX_BUF_SIZE);

            int num = client.read(input);
            input.flip();

            int size = input.remaining() - 80;
            byte[] tmp = new byte[size];

            input.get(tmp);

            String kor = new String(tmp, "EUC-KR");
//            String kor = new String(tmp, StandardCharsets.UTF_8);
//            String kor = new String(tmp);

            System.out.println("한글 문자열: " + kor);

            int[] intArray = new int[10];
            float[] floatArray = new float[10];
            for (int idx = 0; idx < 10; idx++) {
                intArray[idx] = input.getInt();
                floatArray[idx] = input.getFloat();
            }

            System.out.println("int 형 숫자 10 개");
            for (int idx = 0; idx < 10; idx++) {
                System.out.print(intArray[idx] + " ");
            }

            System.out.println();

            System.out.println("float 형 숫자 10 개");
            for (int idx = 0; idx < 10; idx++) {
                System.out.print(floatArray[idx] + " ");
            }

            int maxInt = intArray[0];
            float maxFloat = floatArray[0];
            for (int idx = 1; idx < 10; idx++) {
                if (maxInt < intArray[idx]) {
                    maxInt = intArray[idx];
                }
                if (maxFloat < floatArray[idx]) {
                    maxFloat = floatArray[idx];
                }
            }

            System.out.println("\n\nMAX INT = " + maxInt);
            System.out.println("MAX FLOAT = " + maxFloat);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}