package org.gmp.folderscanner;

import java.io.File;
import java.util.concurrent.RecursiveTask;

public class FolderScanner extends RecursiveTask<FileScannerResult> {
    private File folder;

    public FolderScanner(File file) {
        this.folder = file;
    }

    @Override
    protected FileScannerResult compute() {
        FileScannerResult result = new FileScannerResult(0, 0 ,0);
        long size = 0;
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    FolderScanner task = new FolderScanner(file);
                    task.fork();
                    FileScannerResult newResult =  task.join();
                    result.size += newResult.size;
                    result.folderCount += newResult.folderCount;
                    result.fileCount += newResult.fileCount;
                }

                if(file.isDirectory()){
                    result.folderCount += 1;
                }else{
                    result.size += file.length();
                    result.fileCount += 1;
                }

            }
        }
        return result;
    }
}

class FileScannerResult {
    public int fileCount;
    public int folderCount;
    public int size;

    public FileScannerResult(int fileCount, int folderCount, int size){
        this.fileCount = fileCount;
        this.folderCount = folderCount;
        this.size = size;
    }


}
