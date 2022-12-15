package org.gmp.factorial;

import org.gmp.common.SequentialMethod;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        final BigInteger FACTORIAL_N = BigInteger.valueOf(10);

        SequentialMethod sequentialMethod = new SequentialMethod();
        ForkJoinPool pool = ForkJoinPool.commonPool();

        double facStart = System.currentTimeMillis();
        BigInteger result = sequentialMethod.sequentialFactorial(FACTORIAL_N);
        double facEnd = System.currentTimeMillis();
        System.out.format("Sequential Factorial  Time: %.3f ms " + result.toString(), (facEnd - facStart));


        FJPFactorial fjpFactorial = new FJPFactorial(FACTORIAL_N);
        double facFjpStart = System.currentTimeMillis();
        BigInteger result2 = pool.invoke(fjpFactorial);
        double facFjpEnd = System.currentTimeMillis();
        System.out.format("\nFJP Factorial  Time: %.3f ms " + result2.toString(), (facFjpEnd - facFjpStart));

    }
}
