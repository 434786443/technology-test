package cn.bearfang.apo.test.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
        try{
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(sourceFile));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
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

    public static boolean copyStringFile(File source, File target, String charsetName){
        if(source == null || target == null){
            return false;
        }
        if(!source.exists()){
            return false;
        }

        try{Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source),charsetName));
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target), charsetName));
            int c;
            while((c = reader.read()) !=-1){
                writer.write(c);
            }
            writer.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
            return false;
        }

        return true;
    }

    public static void nioCopyFile(String sourceName, String targetName) {

        try{FileInputStream fileInputStream = new FileInputStream(sourceName);
            FileOutputStream fileOutputStream = new FileOutputStream(targetName);
            FileChannel sourceChannel = fileInputStream.getChannel();
            FileChannel targetChannel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            while ((sourceChannel.read(byteBuffer)) != -1){
//                byteBuffer.flip();//读写转换
//                targetChannel.write(byteBuffer);
//                byteBuffer.clear();//重要，不然每次read都只会返回0
//            }
            //快速拷贝
            targetChannel.transferFrom(sourceChannel,0,sourceChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
