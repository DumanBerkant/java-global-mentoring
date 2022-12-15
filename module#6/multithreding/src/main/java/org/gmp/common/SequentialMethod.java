package org.gmp.common;

import java.math.BigInteger;

public class SequentialMethod{

    public BigInteger sequentialFactorial(BigInteger number){

        if(number.equals(new BigInteger("1")))
            return new BigInteger("1");
        else
            return number.multiply(sequentialFactorial(number.subtract(new BigInteger("1"))));
    }


    public void mergeSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        int[] firstHalf = new int[array.length / 2];
        int[] secondHalf = new int[array.length - firstHalf.length];
        System.arraycopy(array, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(array, firstHalf.length, secondHalf, 0, secondHalf.length);


        mergeSort(firstHalf);
        mergeSort(secondHalf);

        merge(firstHalf, secondHalf, array);
    }

    private void merge(int[] firstHalf, int[] secondHalf, int[] result) {
        int iFirst = 0;
        int iSecond = 0;

        for (int iResult = 0; iResult < result.length; iResult++) {
            if (iSecond >= secondHalf.length || (iFirst < firstHalf.length && firstHalf[iFirst] < secondHalf[iSecond])) {
                result[iResult] = firstHalf[iFirst];
                iFirst++;
            } else {
                result[iResult] = secondHalf[iSecond];
                iSecond++;
            }
        }
    }

}

