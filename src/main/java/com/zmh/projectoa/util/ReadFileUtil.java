package com.zmh.projectoa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zmh
 * @date 2018/2/1813:42
 */
public class ReadFileUtil {
    private final static Logger logger = LoggerFactory.getLogger(ReadFileUtil.class);

    /**
     * 获取文件夹下的文件名
     *
     * @param filePath 文件夹全路径
     * @return 文件名的集合
     */
    public static List<String> getFileName(String filePath) {
        StringBuffer sb = new StringBuffer();
        List<String> list = new ArrayList<>();
        try {
            sb.append("{'fileName':[");
            File file = new File(filePath);
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    list.add(f.getName());
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    /**
     * 传入文件全路径 读取文件内容
     *
     * @param filePath 文件全路径
     * @return 文件内容
     */
    public static String readFileByLines(String filePath, String fileName,String charSet) {
        StringBuffer sb = new StringBuffer();
        try {
            File file = new File(filePath + "/" + fileName);
            BufferedReader reader=new BufferedReader(new InputStreamReader
                    (new FileInputStream(file),charSet));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString + "<br/>");
            }
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return sb.toString();
    }
}
