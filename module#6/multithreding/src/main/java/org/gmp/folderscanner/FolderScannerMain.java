package org.gmp.folderscanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ForkJoinPool;

public class FolderScannerMain {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ForkJoinPool pool = ForkJoinPool.commonPool();

        //******************************************** FOLDER SCANNER ********************************************
        System.out.println("Please enter folder path to scan: ");
        String filePath = bufferedReader.readLine();

        File file = new File(filePath);
        FolderScanner folderScanner = new FolderScanner(file);
        FileScannerResult scannerResult = pool.invoke(folderScanner);


        System.out.println("File Count: " + scannerResult.fileCount);
        System.out.println("Folder Count: " + scannerResult.folderCount);
        System.out.println("Total Size: " + scannerResult.size + "kb\n\n");
    }
}