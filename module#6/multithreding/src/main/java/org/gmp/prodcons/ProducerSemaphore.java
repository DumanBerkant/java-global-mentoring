package org.gmp.prodcons;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerSemaphore extends Thread {

    private Queue queue;
    private Semaphore semaphore;


    public ProducerSemaphore(Semaphore semaphore, Queue queue){
        this.queue = queue;
        this.semaphore = semaphore;
    }

    public void run(){
        for (int i=0; i<10; i++){
            try {
                semaphore.acquire();

                this.queue.add("Data:" + i);
                System.out.println("Data Sended");
                Thread.sleep(500);
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
