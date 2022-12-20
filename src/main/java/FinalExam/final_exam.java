//package FinalExam;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.InetSocketAddress;
//import java.net.SocketAddress;
//import java.net.UnknownHostException;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//
//public class final_exam {
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        Client client = new Client();
//        client.connect();
//    }
//}
//
//class Client {
//    private String ip = "203.252.148.148";
//    private int port = 2021;
//
//    private SocketChannel client = null;
//    private final int MAX_BUF_SIZE = 65507;
//
//    public Client() {}
//
//    public void connect() {
//        try {
//            SocketAddress address = new InetSocketAddress(ip, port);
////            if(client!=null){
////                client.configureBlocking(false);
////            }
//
//            // Client 소켓 채널 Open
//            client = SocketChannel.open();
//            client.connect(address);
//
//            // 학번 입력
//            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//            String theLine = userInput.readLine();
//
//            // Buffer output 할당
//            ByteBuffer output = ByteBuffer.allocate(MAX_BUF_SIZE);
//
//            // Output Buffer 에 입력한 학번 할당
//            for (int i = 0; i < theLine.length(); i++) {
//                char ch = theLine.charAt(i);
//                output.putChar(ch);
//            }
//
//            // Buffer flip
//            output.flip();
//
//            // Client 전송
//            client.write(output);
//
//            // Buffer input 할당
//            ByteBuffer input = ByteBuffer.allocate(MAX_BUF_SIZE);
//
//            // Reads a sequence of bytes from this channel into the given buffer.
//            int num = client.read(input);
//
//            // return The number of bytes read
//            System.out.println(num);
//            input.flip();
//
//            // Read KOR 문자열
//            int size = input.remaining() - 80;
//            byte[] tmp = new byte[size];
//
//            // bulk method - This method transfers bytes from this buffer into the given destination array.
//            input.get(tmp);
//            String kor = new String(tmp, "EUC-KR");
//            System.out.println("한글 문자열: " + kor);
//
//            // int, float 배열 할당
//            int[] intArray = new int[10];
//            float[] floatArray = new float[10];
//            for (int idx = 0; idx < 10; idx++) {
//                intArray[idx] = input.getInt(); // Reads the next four bytes at this buffer's current position
//                floatArray[idx] = input.getFloat(); // Reads the next four bytes at this buffer's current position
//            }
//
//            // buffer 의 pos 와 limit 확인
//            System.out.println("\n모든 데이터를 읽었는지 확인");
//            System.out.println("(position, limit) = " + "(" + input.position() + ", " + input.limit() + ")\n");
//
//            // Int 배열 출력
//            System.out.println("IntArray");
//            for (int idx = 0; idx < 10; idx++) {
//                System.out.print(intArray[idx] + " ");
//            }
//
//            // Float 배열 출력
//            System.out.println("\nFloatArray");
//            for (int idx = 0; idx < 10; idx++) {
//                System.out.print(floatArray[idx] + " ");
//            }
//
//            // MAX int 와 MAX float 탐색
//            int maxInt = intArray[0];
//            float maxFloat = floatArray[0];
//            for (int idx = 1; idx < 10; idx++) {
//                if (maxInt < intArray[idx]) {
//                    maxInt = intArray[idx];
//                }
//                if (maxFloat < floatArray[idx]) {
//                    maxFloat = floatArray[idx];
//                }
//            }
//
//            // 결과 출력
//            System.out.println("\n\nMAX INT = " + maxInt);
//            System.out.println("MAX FLOAT = " + maxFloat);
//
//        } catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            if (client != null) {
//                try {
//                    client.close();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}