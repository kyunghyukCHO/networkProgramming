package Thread.ProducerConsumer;
//import java.util.LinkedList;
//import java.util.Queue;

class Buffer {
//    Queue<Integer> queue = new LinkedList<>
    private int contents;
    private boolean available = false;

    public synchronized int get() {
//        notify();
        while(true) {
            if (available == false) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("contents at Buffer.get() = " + contents);
                available = false;
                notify();
                return contents;
            }
        }
    }

    public synchronized void put(int value) {
//        notify();
        if(available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            contents = value;
            available = true;
            notify();
        }
    }
}