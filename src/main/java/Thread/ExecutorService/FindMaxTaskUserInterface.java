package Thread.ExecutorService;

public class FindMaxTaskUserInterface {
    public static void main(String[] args) {
        int[] data = { 1, 10, 2, 3, 5, 8, 4, 6, 9 };

        FindMaxTask fmt = new FindMaxTask(data,0,data.length);

        System.out.println("=== Single-threaded FindMaxTask ===");

        try {
            long beforeTime = System.currentTimeMillis();
            System.out.println("maxValue = " + fmt.call());
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime);
            System.out.println("run-time : " + secDiffTime);
        } catch ( Exception e) {
            e.printStackTrace();
        }

        System.out.println("=== Multi-threaded FindMaxTask ===");

        try {
            long beforeTime = System.currentTimeMillis();
            System.out.println("maxValue = " + MultithreadedMaxFinder.max(data));
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime);
            System.out.println("run-time : " + secDiffTime);
        } catch ( Exception e) {
            e.printStackTrace();
        }

    }
}
