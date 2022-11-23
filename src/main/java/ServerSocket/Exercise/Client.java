package ServerSocket.Exercise;

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            URL url = new URL("localhost/");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty("Content-Type","text/html");
            httpCon.setRequestMethod("DELETE");
            httpCon.connect();
        } catch (IOException ex) {

        }
    }
}
