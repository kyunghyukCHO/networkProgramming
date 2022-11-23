package ServerSocket.Exercise;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
//        String hostname = args.length > 0 ? args[0] : "utcnist.colorado.edu";
        String hostname = "localhost";
        Socket socket = null;
        try {
            socket = new Socket(hostname, 13);
            socket.setSoTimeout(10000);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

            bw.write("message");
            bw.flush();

            StringBuilder msg = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            for (int c = reader.read(); c != -1; c = reader.read()) {
                msg.append((char) c);
            }
            System.out.println(msg);
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {

                }
            }
        }

    }
}
