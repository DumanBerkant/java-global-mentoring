package org.gmp.fibonacci;

import java.util.concurrent.RecursiveTask;

public class FJPFibonacci extends RecursiveTask<Integer> {
    final int n;

    public FJPFibonacci(int n) {
        this.n = n;
    }

    @Override
    public Integer compute() {
        if (n <= 10)
            return linearFibonacci(n);

        FJPFibonacci f1 = new FJPFibonacci(n - 1);
        f1.fork();
        FJPFibonacci f2 = new FJPFibonacci(n - 2);
        return f2.compute() + f1.join();

    }

    public Integer linearFibonacci(Integer n){
        int fib1 = 1;
        int fib2 = 1;
        int fib = 2;
        for (int i = 3; i < n; i++) {
            fib1 = fib2;
            fib2 = fib;
            fib = fib1 + fib2;
        }
        return fib;
    }
}
