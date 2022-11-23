package MiddleTest;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class zzzz {

    static int NoFileCount = 0;
    static double MAX = Double.MIN_VALUE;
    static double MIN = Double.MAX_VALUE;
    static int max_column = 0;
    static int max_row = 0;
    static int max_c = 0;
    static int max_d = 0;
    static int min_column = 0;
    static int min_row = 0;
    static int min_c = 0;
    static int min_d = 0;

    public static void main(String[] args) {
        for (int i = 30; i <= 40; i++) {
            for (int j = 1; j <= 50; j++) {
                System.out.println("i + "+i+" j ="+j);
                FileViewer(i,j);
            }
        }
        System.out.println("max c = " + max_c);
        System.out.println("max d = " + max_d);
        System.out.println("max row = " + max_row);
        System.out.println("max column = " + max_column);
        System.out.println("max = " + MAX);
        System.out.println("======================");
        System.out.println("min c = " + min_c);
        System.out.println("min d = " + min_d);
        System.out.println("min row = " + min_row);
        System.out.println("min column = " + min_column);
        System.out.println("min = " + MIN);
        System.out.println("======================");
        System.out.println("NoFileCount = " + NoFileCount);

    }
    public static void FileViewer(int c, int d) {

        InputStream in = null;
        FileOutputStream fos = null;
        try {
            URL u = new URL("http://home.konkuk.ac.kr/~leehw/Site/nptest/2021/MidTerM/file%20{c=" + Integer.toString(c) + "}_{d=" + Integer.toString(d) + "}.txt");
            in = u.openStream();
            in = new BufferedInputStream(in);
            fos = new FileOutputStream("src/main/java/MiddleTest/Testing01.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int readCount = 0;
            byte[] buffer = new byte[512];
            while((readCount=in.read(buffer))!=-1) {
                bos.write(buffer,0,readCount);
            }

            BufferedReader reader = new BufferedReader( new FileReader("src/main/java/MiddleTest/Testing01.txt") );
            ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
            String str;
            while ((str = reader.readLine()) != null) {
                if( str.trim().isEmpty() ) continue;
                String[] strings = str.split("\t");
                ArrayList<String> a1 = new ArrayList<String>();
                for( int i = 0; i < strings.length; i++ ) {
                    a1.add( strings[i].trim() );
                }
                list.add( a1 );
            }

            new FileWriter("src/main/java/MiddleTest/Testing01.txt", false).close();

            double maxx = Double.MIN_VALUE;
            int rw = 0;
            int cl = 0;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    double tmp = Double.parseDouble(list.get(i).get(j));
                    if ( tmp >=maxx) {
                        maxx = tmp;
                        rw = i;
                        cl = j;
                    }
                }
            }
            if (maxx >= MAX) {
                max_row = rw; max_column = cl; MAX = maxx; max_c = c; max_d = d;
            }

            double minn = Double.MAX_VALUE;
            rw = 0; cl = 0;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    double tmp = Double.parseDouble(list.get(i).get(j));
                    if ( tmp <= minn) {
                        minn = tmp;
                        rw = i;
                        cl = j;
                    }
                }
            }
            if (minn <= MIN) {
                min_row = rw; min_column = cl; MIN = minn; min_c = c; min_d = d;
            }

        } catch (IOException ex) {
            NoFileCount ++ ;

        } catch (Exception e) {

        } finally {
            try {
                if (in != null) { in.close(); }
            } catch (IOException e) {}
        }
    }
}

