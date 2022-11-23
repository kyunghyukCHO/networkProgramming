package ServerSocket.Exercise;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class EchoServer {

    public final static int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = server.accept()) {
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String message = br.readLine();

                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(message);
                    out.flush();
                    connection.close();
                } catch (IOException ex) {}
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
