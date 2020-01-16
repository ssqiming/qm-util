package com.qm.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Slf4j
public class FileUtil {
    public FileUtil() {
    }

    public static void isHasFileDir(String filePath) {
        String zipFileDir = getStandardUrl(filePath);
        if(zipFileDir.indexOf("/") != -1) {
            zipFileDir = zipFileDir.substring(0, zipFileDir.lastIndexOf("/"));
            File zipFileDirFile = new File(zipFileDir);
            if(!zipFileDirFile.exists()) {
                zipFileDirFile.mkdirs();
            }
        }

    }

    public static String getStandardUrl(String path) {
        return forceReplace(path, "\\", "/");
    }

    public static String forceReplace(String str, String oldStr, String newStr) {
        while(str.indexOf(oldStr) != -1) {
            str = str.substring(0, str.indexOf(oldStr)) + newStr + str.substring(str.indexOf(oldStr) + oldStr.length(), str.length());
        }

        return str;
    }

    public static byte[] encodeBase64File(String path) throws Exception {
        File file = new File(path);
        if (file == null) {
            return null;
        }
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return (new Base64()).encode(buffer);
    }

    public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
        isHasFileDir(targetPath);
        new Base64();
        byte[] buffer = Base64.decodeBase64(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    public static void toFile(String base64Code, String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * @Description 递归获取目录下指定后缀文件
     * @Author qiming
     * @Date 17:42 2020/1/11
     **/
    public static List<String> getFiles(String basePath, String filePrefix) {
        if (StringUtils.isBlank(filePrefix)) {
            return null;
        }
        List<String> pathList = Lists.newArrayList();
        File file = new File(basePath);
        File files[] = file.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }
        for (File ff : files) {
            if (ff.isDirectory()) {
                pathList.addAll(getFiles(ff.getAbsolutePath(), filePrefix));
            } else if (ff.isFile() && ff.getName().startsWith(filePrefix)) {
                pathList.add(ff.getAbsolutePath());
            } else {
                log.info("未知文件前缀, 文件地址为: " + ff.getAbsolutePath());
            }
        }
        return pathList;
    }

    /**
     * @Description 递归删除文件夹及文件夹下的所有文件
     * @Author qiming
     * @Date 10:59 2020/1/16
     **/
    public static boolean deleteDir(String path) {
        File file = new File(path);
        //判断是否待删除目录是否存在
        if (!file.exists()) {
            return false;
        }
        //取得当前目录下所有文件和文件夹
        String[] content = file.list();
        for (String name : content) {
            File temp = new File(path, name);
            //判断是否是目录
            if (temp.isDirectory()) {
                //递归调用，删除目录里的内容
                deleteDir(temp.getAbsolutePath());
                //删除空目录
                temp.delete();
            } else {
                //直接删除文件
                if (!temp.delete()) {
                    return false;
                }
            }
        }
        // 删除最外层文件夹
        file.delete();
        return true;
    }

    /**
     * @Description 删除文件
     * @Author qiming
     * @Date 11:04 2020/1/16
     **/
    public static boolean deleteFile(String path) {
        File file = new File(path);
        // 文件不存在或非文件类型
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        return file.delete();
    }
}
