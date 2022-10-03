package Thread.ProducerConsumer;

public class ProducerConsumerProblem {

    public static void main(String[] args) {
        Buffer b = new Buffer();
        Producer p1 = new Producer(b);
        Consumer c1 = new Consumer(b);

        p1.start();
        c1.start();

    }

}
