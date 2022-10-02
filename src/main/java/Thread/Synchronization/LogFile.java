package Thread.Synchronization;
import java.io.*;
import java.util.Date;

public class LogFile {

    private Writer out;

    public LogFile(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        this.out = new BufferedWriter(fw);
    }

    // Problem : log info can mix up due to multiple threads interrupting each other
//    public void writeEntry(String message) throws IOException {
//        Date d = new Date();
//        out.write(d.toString());
//        out.write('\t');
//        out.write(message);
//        out.write("\r\n");
//    }

    // Solution 1 : Only a single thread can execute the code inside the synchronized block
//    public void writeEntry(String message) throws IOException {
//        synchronized (out) { // Writer Object Synchronize
//            Date d = new Date();
//            out.write(d.toString());
//            out.write('\t');
//            out.write(message);
//            out.write("\r\n");
//        }
//    }

    // Solution 2 : Only a single thread can execute the code inside the synchronized block
//    public void writeEntry(String message) throws IOException {
//        synchronized (this) { // LogFile Object Synchronize
//            Date d = new Date();
//            out.write(d.toString());
//            out.write('\t');
//            out.write(message);
//            out.write("\r\n");
//        }
//    }

    // Solution 3 : Only a single thread can execute the code inside the synchronized block
    public synchronized void writeEntry(String message) throws IOException {  // method Synchronize
        Date d = new Date();
        out.write(d.toString());
        out.write('\t');
        out.write(message);
        out.write("\r\n");
    }


    public void close() throws IOException {
        out.flush();
        out.close();
    }


}
