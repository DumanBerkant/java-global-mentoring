package org.gmp.fibonacci;


import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;

public class FJPFibonacciTest {

    @Test
    public void test(){

        assertEquals(1134903170L, new ForkJoinPool().invoke(new FJPFibonacci(45)).longValue());
    }

}