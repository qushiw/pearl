package web.java.file;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: qushiwen
 * @Date: 18-11-25
 * @version: 1.0
 */
public class BatchModifyFileName {

    private static final AtomicInteger index = new AtomicInteger(0);
    private static final String regularExpression = "_[0-9]*";

    public static void main(String[] args) {
        modify("/home/qushiwen/temp/picture");
    }



    public static void modify(String filePath) {

        if (filePath == null || filePath.equals("")) {
            return;
        }

        File file = new File(filePath);
        boolean isDirectory = file.isDirectory();
        if (!isDirectory) {
            System.err.println("###### is not a folder.");
            return;
        }

        String[] files = file.list();
        File tempFile = null;
        String newFIleName = "";
        String oldFileName = "";
        for (String fileName : files) {
            oldFileName = fileName;
            fileName = fileName.replaceAll(regularExpression, "");
            tempFile = new File(filePath + "/" + oldFileName); //输出地址和原路径保持一致
            tempFile.renameTo(new File(filePath + "/" +  fileName));
            System.out.println("###########  change fileName:"+ oldFileName + ", to new Name : " + fileName);
            index.incrementAndGet();
        }
        System.out.println("######## batch modify file name success. total num : " + index.get());
    }

}
