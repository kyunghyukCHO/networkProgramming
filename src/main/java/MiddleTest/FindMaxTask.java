package MiddleTest;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class FindMaxTask implements Callable<double[]> {
    private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
    private int start;
    private int end;

    public FindMaxTask(ArrayList<ArrayList<String>> list, int start, int end) {
        this.list = list;
        this.start = start;
        this.end = end;
    }

    @Override
    public double[] call() throws Exception {

        double maxx = Double.MIN_VALUE;
        double minn = Double.MAX_VALUE;
        double row_max = 0;
        double col_max = 0;
        double row_min = 0;
        double col_min = 0;
        double sum = 0;
        double cnt = 0;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                cnt +=1;
                sum += Double.parseDouble(list.get(i).get(j));
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

        double[] answer = {maxx,row_max,col_max,minn,row_min,col_min,sum/cnt};

        return answer;
    }
}
