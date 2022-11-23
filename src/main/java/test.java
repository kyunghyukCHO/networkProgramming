import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class test {
    public static void main(String[] args) {
        InputStream in = System.in;
        OutputStream out = System.out;

        try {
            int input = in.read();
            System.out.println(81);
            int a = 81;
            System.out.write(a);
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
