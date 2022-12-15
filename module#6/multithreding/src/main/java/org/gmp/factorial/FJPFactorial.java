package org.gmp.factorial;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FJPFactorial extends RecursiveTask<BigInteger> {

    private BigInteger number;

    public FJPFactorial(BigInteger number) {
        this.number = number;
    }

    @Override
    protected BigInteger compute() {

        if (number.equals(new BigInteger("1")) || number.equals(new BigInteger("0")))
            return new BigInteger("1");


        FJPFactorial f1 = new FJPFactorial(number.subtract(new BigInteger("1")));
        f1.fork();


        return number.multiply(f1.join());


    }
}
