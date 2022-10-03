package Thread.ProducerConsumer;

class Consumer extends Thread {
    private Buffer blank;

    public Consumer(Buffer blank) {
        this.blank = blank;
    }

    public void run() {
        int value = 0;

        for (int i=0; i<10; i++) {
            value = blank.get();

            System.out.println("Consumer: Consumed" + value);
        }
    }
}