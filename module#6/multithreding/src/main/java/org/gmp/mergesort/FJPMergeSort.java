package org.gmp.mergesort;

import java.util.concurrent.RecursiveAction;

public class FJPMergeSort extends RecursiveAction {
    private int[] array;


    public FJPMergeSort(int[] array) {
        this.array = array;
    }

    @Override
    protected void compute() {

        if (array.length <= 1) {
            return;
        }

        int[] firstHalf = new int[array.length / 2];
        int[] secondHalf = new int[array.length - firstHalf.length];
        System.arraycopy(array, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(array, firstHalf.length, secondHalf, 0, secondHalf.length);

        FJPMergeSort f1 = new FJPMergeSort(firstHalf);
        FJPMergeSort f2 = new FJPMergeSort(secondHalf);

        //invokeAll(f1, f2);
        f1.fork();
        f2.compute();

        merge(firstHalf, secondHalf, array);
        f1.join();
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
