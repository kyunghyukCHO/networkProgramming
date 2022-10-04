package OfflineExercise;

/*
 숫자가 클수록 오래걸립니다; input의 size 가 아닌 value의 크기 에 영향을 받습니다.
 */

public class SleepSorting extends Thread {
    private int num;

    public SleepSorting(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(num * 50);
            System.out.print(num+" ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] nums = {5,2,9,10,0,4,6,1,3,7,8};
        for (int num : nums) {
            SleepSorting st = new SleepSorting(num);
            Thread th = new Thread(st);
            th.start();
        }
    }
}
