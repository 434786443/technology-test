package cn.bearfang.apo.test.utils;

import java.io.*;

/**
 * @program: technology-test
 * @description:
 * @author: bearfang
 * @create: 2020-11-29 12:24
 **/
public class IOUtils {

    public static boolean copyFile(File sourceFile, File targetFile){
        if(!sourceFile.exists()){
            return false;
        }
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(targetFile))){
//            byte[] b = new byte[1024];
//            while (fileInputStream.read(b) != -1){
//                fileOutputStream.write(b);
//            }
//            fileOutputStream.flush();
            int c;
            while ((c = bufferedInputStream.read()) != -1){
                bufferedOutputStream.write(c);
            }
            bufferedOutputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }


}
