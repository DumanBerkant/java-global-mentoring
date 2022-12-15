package org.gmp.fibonacci;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class SumOfSquaresMain {
    public static void main(String[] args) {
        System.out.println("Calculating...");

        ForkJoinPool pool = ForkJoinPool.commonPool();
        double[] array = new Random().doubles(500_000_000).toArray();

        double fjpStart = System.currentTimeMillis();
        double sum = sumOfSquares(pool, array);
        double fjpEnd = System.currentTimeMillis();
        System.out.format("\nRecursiveAction Time: %.3f ms  Sum: %f", (fjpEnd - fjpStart), sum);

        double linearStart = System.currentTimeMillis();
        double sum2 = sumOfSquaresLinear(array);
        double linearEnd = System.currentTimeMillis();
        System.out.format("\nLinear Action Time: %.3f ms Sum: %f", (linearEnd - linearStart), sum2);

    }

    static double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        FJPSumOfSquares a = new FJPSumOfSquares(array, 0, n, null);
        pool.invoke(a);
        return a.result;
    }

    static double sumOfSquaresLinear(double[] array) {
        double sum = 0;
        for (double d : array) {
            sum += d * d;
        }
        return sum;
    }
}
