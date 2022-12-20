package MiddleTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


class SearchFile implements Callable<double[]> {

    private int start_c;
    private int end_c;
    private int start_d;
    private int end_d;
    private String filename;

    public SearchFile(int start_c, int end_c, int start_d, int end_d, String filename) {
        this.start_c = start_c;
        this.end_c = end_c;
        this.start_d = start_d;
        this.end_d = end_d;
        this.filename = filename;
    }

    int NoFileCount = 0;
    double MAX = Double.MIN_VALUE;
    double MIN = Double.MAX_VALUE;
    double max_column = 0;
    double max_row = 0;
    double max_c = 0;
    double max_d = 0;
    double min_column = 0;
    double min_row = 0;
    double min_c = 0;
    double min_d = 0;
    double avg = 0;

    @Override
    public double[] call() throws Exception {
        for (int i = start_c; i <= end_c; i++) {
            for (int j = start_d; j <= end_d; j++) {
                System.out.println("i = " + i + " j = " + j);
                Fileviewer(i,j);
            }
        }
        double[] answer ={MAX, max_row, max_column, max_c, max_d, MIN, min_row, min_column, min_c, min_d, NoFileCount,avg};
        return answer;
    }

    private InputStream testing(int c, int d) {
        InputStream in = null;
        String strr = "http://home.konkuk.ac.kr/~leehw/Site/nptest/2021/MidTerM/file%20{c=" + Integer.toString(c) + "}_{d=" + Integer.toString(d) + "}.txt";
        int cnt = 0;
        while (true) {
            if (cnt == 2) { NoFileCount++; return null;}
            try {
                URL u = new URL(strr);
                return u.openStream();
            } catch (FileNotFoundException exception) {
                strr = "http://home.konkuk.ac.kr/~leehw/Site/nptest/2021/MidTerM/file%20(c=" + Integer.toString(c) + ")_(d=" + Integer.toString(d) + ").txt";
                cnt++;
            } catch (MalformedURLException e) {
            } catch (IOException ex) {
            }
        }
    }

    public void Fileviewer(int c, int d) {
        InputStream in = null;
        FileOutputStream fos = null;
        try {
            in = testing(c,d);
            if (in == null) {return;}

            in = new BufferedInputStream(in);
            fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int readCount = 0;
            byte[] buffer = new byte[512];
            while((readCount=in.read(buffer))!=-1) {
                bos.write(buffer,0,readCount);
            }

            BufferedReader reader = new BufferedReader( new FileReader(filename) );
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

            new FileWriter(filename, false).close();

            double maxx = 0;
            double minn = 0;
            double row_max = 0;
            double col_max = 0;
            double row_min = 0;
            double col_min = 0;

            if (list.size()>1) {
                FindMaxTask task1 = new FindMaxTask(list,0,list.size()/2);
                FindMaxTask task2 = new FindMaxTask(list,list.size()/2, list.size());

                ExecutorService service2 = Executors.newFixedThreadPool(2);

                Future<double[]> future3 = service2.submit(task1);
                Future<double[]> future4 = service2.submit(task2);

                double[] result3 = future3.get();
                double[] result4 = future4.get();

                // double[] answer = {maxx,row_max,col_max,minn,row_min,col_min, avg};

                if(result3[0]>=result4[0]) {
                    maxx = result3[0];
                    row_max = result3[1];
                    col_max = result3[2];
                } else {
                    maxx = result4[0];
                    row_max = result4[1];
                    col_max = result4[2];
                }

                if(result3[3]<=result4[3]) {
                    minn = result3[3];
                    row_min = result3[4];
                    col_min = result3[5];
                } else {
                    minn = result4[3];
                    row_min = result4[4];
                    col_min = result4[5];
                }

                avg = ( result3[6]+result4[6] ) / 2 ;
            } else {
                double sum = 0;
                double cnt = 0;
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.get(i).size(); j++) {
                        sum+=Double.parseDouble(list.get(i).get(j));
                        cnt+=1;
                        double tmp = Double.parseDouble(list.get(i).get(j));
                        if ( tmp >=maxx) {
                            maxx = tmp;
                            row_max = i;
                            col_max = j;
                        }
                        if (tmp <=minn) {
                            minn = tmp;
                            row_min = i;
                            col_min = j;
                        }
                    }
                }
                avg = sum/cnt;
            }

            if (maxx >= MAX) {
                max_row = row_max; max_column = col_max; MAX = maxx; max_c = c; max_d = d;
            }

            if (minn <= MIN) {
                min_row = row_min; min_column = col_min; MIN = minn; min_c = c; min_d = d;
            }

        } catch(IOException ex) {

        } catch (Exception e) {

        } finally {
            try {
                if (in != null) { in.close(); }
            } catch (IOException e) {}
        }

    }
}
