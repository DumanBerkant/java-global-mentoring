package org.gmp.prodcons;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

public class ProdConsMain {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        //******************************************** PRODS-CONS BLOCKING QUEUE ********************************************
        BlockingQueue blockingQeueu = new ArrayBlockingQueue<String>(5);
        //new Producer(blockingQeueu).start();
        //new Consumer(blockingQeueu).start();
        //------------------------------------------ PRODS-CONS BLOCKING QUEUE ------------------------------------------




        //******************************************** PRODS-CONS SEMAPHORE ********************************************
        Queue normalQueue = new LinkedList();
        Semaphore semaphore = new Semaphore(2);
        new ConsumerSemaphore(semaphore, normalQueue).start();
        new ProducerSemaphore(semaphore, normalQueue).start();

        //------------------------------------------ PRODS-CONS SEMAPHORE ------------------------------------------
    }
}
