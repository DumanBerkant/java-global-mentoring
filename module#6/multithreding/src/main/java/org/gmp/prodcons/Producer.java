package org.gmp.prodcons;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                this.queue.add("Data" + i);
                System.out.println("Data sended to queue");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
