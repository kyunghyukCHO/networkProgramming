package Thread;
import java.io.*;
import java.security.*;

public class DigestThread extends Thread {

    private String filename;

    public DigestThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename); // InputStream 생성
            MessageDigest sha = MessageDigest.getInstance("SHA-256"); // Hash 값 계산 -> sha 에 저장
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) ; // DigestInputStream 으로 부터 data read() ( 1byte read -> hash update )
            din.close();
            byte[] digest = sha.digest(); // hash 값 완성 후 byye array 로 return

            StringBuilder result = new StringBuilder(filename);
            result.append(" : ");
            result.append(toHexString(digest));
            System.out.println(result); // result 출력
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
    }

    public static String toHexString(byte[] bytes) { // byte array 를 읽어서 String 변환
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]); // bitwise operation
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public static void main(String[] args) {
        for (String filename : args) { // filename 들을 받아들임
            Thread t = new DigestThread(filename); // 각 file 에 대해 Digest Thread 를 생성
            t.start();
        }
    }
}
