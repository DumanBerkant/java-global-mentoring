package org.gmp.prodcons;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ConsumerSemaphore extends Thread {

    private Queue<String> queue;
    private Semaphore semaphore;

    public ConsumerSemaphore(Semaphore semaphore, Queue queue) {
        this.semaphore= semaphore;
        this.queue = queue;
    }


    public void run() {
        while (true) {
            try {
                semaphore.acquire();
                if (!queue.isEmpty()) {
                    String data = queue.remove();
                    System.out.println("Incoming data is: " + data);
                    Thread.sleep(300);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
