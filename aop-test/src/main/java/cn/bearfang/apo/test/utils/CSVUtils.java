package cn.bearfang.apo.test.utils;

//import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * @author bearfang
 * @date 2020/11/24
 */
@Slf4j
public class CSVUtils {

    /**
     * 生成CSV文件的主体方法
     * @param head
     * @param dataList
     * @param outPutPath
     * @param filename
     * @return
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList, String outPutPath, String filename) {
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            log.error("转换csv文件异常 e={}",e);
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("csvWriter close error！e:{}",e);
            }
        }

        return csvFile;
    }

//    /**
//     * 转化为ByteString格式
//     * @param head
//     * @param dataList
//     * @param outPutPath
//     * @param filename
//     * @return
//     */
//    public static ByteString createCSVByteString(List<Object> head, List<List<Object>> dataList, String outPutPath, String filename){
//        File csvFile = createCSVFile(head, dataList, outPutPath, filename);
//        ByteString csvByteString = null;
//        //转化为 ByteString
//        try (FileInputStream fis = new FileInputStream(csvFile)){
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            byte[] b = new byte[1024];
//            int n;
//            while ((n = fis.read(b)) != -1)
//            {
//                bos.write(b, 0, n);
//            }
//            fis.close();
//            bos.close();
//            byte[] bytes = bos.toByteArray();
//            csvByteString = ByteString.copyFrom(bytes);
//            csvFile.delete();
//        } catch (Exception e) {
//            log.error("转换csv文件异常 e={}",e);
//        }
//        return csvByteString;
//    }

    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

}
