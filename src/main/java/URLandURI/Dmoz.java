package URLandURI;

import java.io.*;
import java.net.*;

public class Dmoz {
    public static void main(String[] args) {
        String target = "";
        for (int i = 0; i < args.length; i++) {
            target += args[i] + " ";
        }
        target = target.trim();

        QueryString query = new QueryString();
        query.add("q", target);
        try {
            URL u = new URL("https://www.dmoz-odp.org/search?" + query);
            System.out.println(u);
            try (InputStream in = u.openStream()) {
                BufferedReader theHTML = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = theHTML.readLine()) != null) {
                    System.out.print(line);
                }
            }
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (IOException ex){
            System.err.println(ex);
        }


    }
}
