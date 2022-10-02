package Thread.ThreadScheduling;

// 우선순위를 바꿔줄 수는 있지만, 실제적인 스레드의 우선순위는 운영체제가 관리합니다.
// 또한 상황에 따라 그때 그때 달라질 수 있습니다.
// 즉, 어플리케이션 레벨에서는 원하는 결과를 얻을 수 없을 가능성이 큽니다.

public class ThreadPriorityTest extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + " : " + i);
        }
    }

    public static void main(String[] args) {
        ThreadPriorityTest t1 = new ThreadPriorityTest();
        ThreadPriorityTest t2 = new ThreadPriorityTest();

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }
}
