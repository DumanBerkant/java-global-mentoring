package org.gmp.mergesort;

import org.gmp.common.SequentialMethod;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

class CommandScanner extends Thread {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease type Q to exit!");
        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("q")) {
                System.exit(0);
            }else{
                System.out.println("Please type correct command!");
            }
        }
    }
}

public class MergeSortMain {

    public static void main(String[] args) throws InterruptedException {
        final int MERGE_ARRAY_SIZE = 100000000;
        ForkJoinPool pool = ForkJoinPool.commonPool();
        SequentialMethod sequentialMethod = new SequentialMethod();

        Thread scanner = new CommandScanner();
        scanner.setDaemon(true);
        scanner.start();


        System.out.format("\n\nSequential Sorting started...");
        Random random = new Random();
        int[] array = random.ints(MERGE_ARRAY_SIZE).toArray();
        double mergeStart = System.currentTimeMillis();
        sequentialMethod.mergeSort(array);
        double mergeEnd = System.currentTimeMillis();
        System.out.format("\nSequential Merge Sort Time: %.3f ms ", (mergeEnd - mergeStart));


        System.out.format("\nParallel Sorting started...");
        int[] array2 = random.ints(MERGE_ARRAY_SIZE).toArray();
        double mergeFjpStart = System.currentTimeMillis();
        FJPMergeSort fjpMergeSort = new FJPMergeSort(array2);
        pool.invoke(fjpMergeSort);
        double mergeFjpEnd = System.currentTimeMillis();
        System.out.format("\nFJP Merge Sort Time: %.3f ms \n", (mergeFjpEnd - mergeFjpStart));

    }
}
