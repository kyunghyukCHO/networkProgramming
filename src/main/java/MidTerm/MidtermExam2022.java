package MidTerm;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.*;

public class MidtermExam2022 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        int MAX = 0;
        int MIN = 0;
        int NoFileCount = 0;

        long beforeTime = System.currentTimeMillis();

        for (int t = 0; t < 5; t++) {
            int[] result1;
            int[] result2;

            String path = System.getProperty("user.dir");
            String path1 = path + "/Testing01.txt";
            String path2 = path + "/Testing02.txt";

            File file1 = new File(path1);
            if(!file1.exists()){
                file1.createNewFile();
            }
            File file2 = new File(path2);
            if(!file2.exists()){
                file2.createNewFile();
            }

            SearchFile task1 = new SearchFile(1, 50, 1,50, "path1");
            SearchFile task2 = new SearchFile(1, 50, 1,50, "path2");

            ExecutorService service = Executors.newFixedThreadPool(2);

            Future<int[]> future1 = service.submit(task1);
            Future<int[]> future2 = service.submit(task2);

            result1 = future1.get();
            result2 = future2.get();

            if (result1[0] >= result2[0]) {
                MAX = result1[0];
            } else {
                MAX = result2[0];
            }

            if (result1[1] <= result2[1]) {
                MIN = result1[1];
            } else {
                MIN = result2[1];
            }

            NoFileCount = result1[2] + result2[2];

            System.out.println();

        }

        long afterTime = System.currentTimeMillis();
        long Time = (afterTime - beforeTime);

        System.out.println("max = " + MAX + "      min = " + MIN);
        System.out.println("number of missing files = "+NoFileCount);
        System.out.println(Time);
        System.out.println("5번 수행시 평균 running Time : "+ Time/5 );
    }
}

class FindingTask implements Callable<int[]> {
    private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    private int start;
    private int end;

    public FindingTask(ArrayList<ArrayList<String>> list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    public int[] call() throws Exception {
        int maxx = Integer.MIN_VALUE;
        int minn = Integer.MAX_VALUE;
        int max_frequent_num = 0;
        int max_frequent = 0;
        int min_frequent_num = 0;
        int min_frequent = 0;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                // Finding MAX and MIN
                int tmpp = Integer.parseInt(list.get(i).get(j));
                if ( tmpp >=maxx) {
                    maxx = tmpp;
                }
                if (tmpp <=minn) {
                    minn = tmpp;
                }
            }
        }

        int[] answer = {maxx,minn};

        return answer;
    }
}


class SearchFile implements Callable<int[]> {

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
    int MAX = Integer.MIN_VALUE;
    int MIN = Integer.MAX_VALUE;

    @Override
    public int[] call() throws Exception {
        for (int i = start_c; i <= end_c; i++) {
            for (int j = start_d; j <= end_d; j++) {
                Fileviewer(i,j);
            }
        }
        int[] answer ={MAX, MIN, NoFileCount};
        return answer;
    }

    private InputStream testing(int c, int d) {
        InputStream in = null;
        String strr = "http://home.konkuk.ac.kr/~leehw/Site/nptest/2022/midtermOCT18/file%20<c=" + Integer.toString(c) + ">_<d=" + Integer.toString(d) + ">.txt";
        int cnt = 0;
        while (true) {
            if (cnt == 2) { NoFileCount++; return null;}
            try {
                URL u = new URL(strr);
                return u.openStream();
            } catch (FileNotFoundException exception) {
                strr = "http://home.konkuk.ac.kr/~leehw/Site/nptest/2022/midtermOCT18/file%20(c=" + Integer.toString(c) + ")_(d=" + Integer.toString(d) + ").txt";
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
                String[] strings = str.split(" ");
                ArrayList<String> a1 = new ArrayList<String>();
                for( int i = 0; i < strings.length; i++ ) {
                    a1.add( strings[i].trim() );
                }
                list.add( a1 );
            }

            new FileWriter(filename, false).close();

            int maxx = 0;
            int minn = 0;

            if (list.size()>1) {
                FindingTask task1 = new FindingTask(list,0,list.size()/2);
                FindingTask task2 = new FindingTask(list,list.size()/2, list.size());

                ExecutorService service2 = Executors.newFixedThreadPool(2);

                Future<int[]> future3 = service2.submit(task1);
                Future<int[]> future4 = service2.submit(task2);

                int[] result3 = future3.get();
                int[] result4 = future4.get();

                // int[] answer = {maxx,minn};

                if(result3[0]>=result4[0]) {
                    maxx = result3[0];
                } else {
                    maxx = result4[0];
                }

                if(result3[1]<=result4[1]) {
                    minn = result3[1];
                } else {
                    minn = result4[1];
                }
            } else {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.get(i).size(); j++) {
                        int tmp = Integer.parseInt(list.get(i).get(j));
                        if ( tmp >=maxx) {
                            maxx = tmp;
                        }
                        if (tmp <=minn) {
                            minn = tmp;
                        }
                    }
                }
            }

            if (maxx >= MAX) {
                MAX = maxx;
            }

            if (minn <= MIN) {
                MIN = minn;
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


