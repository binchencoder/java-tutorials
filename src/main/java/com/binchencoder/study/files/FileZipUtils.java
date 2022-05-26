package com.binchencoder.study.files;

import static com.binchencoder.study.common.Constant.SYSTEM_FILE_SEP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

/**
 * zip文件解压缩 工具类
 *
 * @author chenbin
 */
@Slf4j
public class FileZipUtils {

    /**
     * zip文件压缩
     *
     * @param inputFile  待压缩文件夹/文件名
     * @param outputFile 生成的压缩包名字
     */
    public static void zipCompress(String inputFile, String outputFile) throws Exception {
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File input = new File(inputFile);
        compress(out, bos, input, null);
        bos.close();
        out.close();
    }

    /**
     * zip递归压缩
     *
     * @param name 压缩文件名，可以写为null保持默认
     */
    public static void compress(ZipOutputStream out, BufferedOutputStream bos, File input,
        String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }

        // 如果路径为目录（文件夹）
        if (input.isDirectory()) {
            // 取出文件夹中的文件（或子文件夹）
            File[] files = input.listFiles();

            // 如果文件夹为空，则只需在目的地zip文件中写入一个目录进入
            if (files.length == 0) {
                out.putNextEntry(new ZipEntry(name + SYSTEM_FILE_SEP));
            } else { // 如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
                for (int i = 0; i < files.length; i++) {
                    compress(out, bos, files[i], name + SYSTEM_FILE_SEP + files[i].getName());
                }
            }
        } else { // 如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
            out.putNextEntry(new ZipEntry(name));
            FileInputStream fos = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int len = -1;
            // 将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bis.close();
            fos.close();
        }
    }

    /**
     * zip解压
     *
     * @param srcZipFile  待解压文件
     * @param destDirPath 解压路径
     */
    public static void zipUncompress(File srcZipFile, String destDirPath) throws IOException {
        // 判断源文件是否存在
        if (!srcZipFile.exists()) {
            throw new FileNotFoundException("解压源文件不存在[" + srcZipFile.getPath() + "]");
        }

        ZipFile zipFile = new ZipFile(srcZipFile);

        ZipArchiveEntry entry;
        // 获取全部文件的迭代器
        Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
        InputStream inputStream;
        while (entries.hasMoreElements()) {
            entry = entries.nextElement();
            if (entry.isDirectory()) {
                continue;
            }

            File outputFile = new File(destDirPath, entry.getName());
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

            inputStream = zipFile.getInputStream(entry);
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                int len;
                byte[] buffer = new byte[1024];
                while ((len = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            } catch (FileNotFoundException e) {
                log.error("FileZipUtils#zipUncompress has error FileNotFoundException", e);
                // pass
            } catch (IOException e) {
                log.error("FileZipUtils#zipUncompress has error IOException", e);
                throw e;
            }
        }
    }
}
