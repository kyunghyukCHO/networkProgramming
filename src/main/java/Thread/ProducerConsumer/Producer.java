package Thread.ProducerConsumer;

class Producer extends Thread {
    private Buffer blank;

    public Producer(Buffer blank) {
        this.blank = blank;
    }

    public void run() {
        for (int i=0; i<10; i++) {
            blank.put(i);

            System.out.println("Producer: Produced" + i);
            try {
                sleep((int)(Math.random()*100));
            } catch (InterruptedException e) {

            }
        }
    }
}