package org.gmp.prodcons;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
    private BlockingQueue<String> queue;


    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    public void run(){
        try {
            while (true){
                String takenData = this.queue.take();
                System.out.println("Incoming data: " + takenData);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
