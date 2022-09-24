package Thread.Returning;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigest extends Thread {
    private String filename;
    private byte[] digest; // 파일의 내용에 기반한 hash 값 저장

    public ReturnDigest(String filename) {
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
            digest = sha.digest();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
    }

    public byte[] getDigest() {
        return digest;
    }


}
