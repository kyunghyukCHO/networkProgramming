package MiddleTest;

import Thread.ExecutorService.FindMaxTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MidTest02 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long beforeTime = System.currentTimeMillis();
        double MAX = 0;
        double MIN = 0;

        double max_row = 0;
        double max_column = 0;
        double max_c = 0;
        double max_d = 0;

        double min_row = 0;
        double min_column = 0;
        double min_c = 0;
        double min_d = 0;

        double avg = 0;

        double NoFileCount = 0;

        double[] result1;
        double[] result2;

        SearchFile task1 = new SearchFile(1, 30, 1, 50, "src/main/java/MiddleTest/Testing01.txt");
        SearchFile task2 = new SearchFile(31, 60, 1,50, "src/main/java/MiddleTest/Testing02.txt");

        ExecutorService service = Executors.newFixedThreadPool(2);

        Future<double[]> future1 = service.submit(task1);
        Future<double[]> future2 = service.submit(task2);
//        double[] answer ={MAX, max_row, max_column, max_c, max_d, MIN, min_row, min_column, min_c, min_d, NoFileCount, avg};

        result1 = future1.get();
        result2 = future2.get();

        avg = (result1[11] + result2[11]) / 2;

        if (result1[0] >= result2[0]) {
            MAX = result1[0];
            max_row = result1[1];
            max_column = result1[2];
            max_c = result1[3];
            max_d = result1[4];

        } else {
            MAX = result2[0];
            max_row = result2[1];
            max_column = result2[2];
            max_c = result2[3];
            max_d = result2[4];
        }

        if (result1[5] <= result2[5]) {
            MIN = result1[5];
            min_row = result1[6];
            min_column = result1[7];
            min_c = result1[8];
            min_d = result1[9];

        } else {
            MIN = result2[5];
            min_row = result2[6];
            min_column = result2[7];
            min_c = result2[8];
            min_d = result2[9];
        }

        NoFileCount = result1[10] + result2[10];

        System.out.println();
        System.out.println("c = "+(int)max_c+"   d = "+(int)max_d+"   row = "+(int)max_row+"   column = "+(int)max_column+"   max = "+MAX);
        System.out.println("c = "+(int)min_c+"   d = "+(int)min_d+"   row = "+(int)min_row+"   column = "+(int)min_column+"   min = "+MIN);

        System.out.println("avg = "+Math.round(avg*1000)/1000.0);

        System.out.println("number of missing files = "+NoFileCount);

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("run-time : " + secDiffTime);
    }
}
