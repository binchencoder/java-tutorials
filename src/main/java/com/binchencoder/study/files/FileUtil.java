package com.binchencoder.study.files;

import com.google.common.base.Strings;
import com.google.common.io.Files;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件操作工具类
 *
 * @author chenbin
 */
@Slf4j
public class FileUtil {

    /**
     * 根据文件名称返回后缀名
     *
     * @param fileName 文件名称
     * @return
     */
    public static String getFileSuffix(String fileName) {
        if (Strings.isNullOrEmpty(fileName)) {
            return "";
        }

        String suffix = "";
        int idx = fileName.lastIndexOf(".");
        if (idx != -1 && idx < fileName.length() - 2) {
            suffix = fileName.substring(idx);
        }
        return suffix;
    }

    /**
     * @param dirFile 目录文件
     */
    public static void mkdirsAddChmod777(File dirFile) {
        if (dirFile.isFile()) {
            log.warn("This file[{}] is not a directory and cannot be created", dirFile.getPath());
            return;
        }

        dirFile.mkdirs();
        try {
            addRChmod777(dirFile);
        } catch (IOException e) {
            log.error("Failed execute chmod -R 777", e);
        }
    }

    /**
     * 将具体内容写入进文件
     *
     * @param file    要写入内容的文件
     * @param content 写入内容
     */
    public static void writeFile(File file, String content) {
        File parentFile = file.getParentFile();
        parentFile.mkdirs();

        if (!file.exists()) {
            try {
                Files.touch(file);
            } catch (IOException e) {
                log.error("Failed create an file, path:{}", file.getPath(), e);
                throw new RuntimeException(e);
            }
        }

        if (Strings.isNullOrEmpty(content)) {
            return;
        }

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
            new FileOutputStream(file))) {
            bufferedOutputStream.write(content.getBytes());
        } catch (FileNotFoundException ffe) {
            log.error("File[{}] not found", file, ffe);
            throw new RuntimeException(ffe);
        } catch (IOException ie) {
            log.error("Write an file error", ie);
            throw new RuntimeException(ie);
        }
    }

    /**
     * 删除文件
     *
     * @param filePath 要删除的文件路径
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        deleteFile(file);
    }

    public static void deleteFile(File file) {
        file.deleteOnExit();
    }

    /**
     * 拷贝文件
     *
     * @param srcFile 源文件
     * @param dstFile 目标文件
     * @throws FileNotFoundException
     */
    public static void copyFile(File srcFile, File dstFile) throws FileNotFoundException {
        String filePath = srcFile.getPath();
        if (!srcFile.exists()) {
            String msg = MessageFormat.format("File[{0}] not found", filePath);
            log.warn("FileUtil#copyFile, {}", msg);
            throw new FileNotFoundException(msg);
        }

        try {
            java.nio.file.Files.copy(Paths.get(srcFile.getPath()), Paths.get(dstFile.getPath()),
                StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("FileUtil#copyFile error", e);
        }
    }

    /**
     * 拷贝文件到指定文件夹
     *
     * @param srcFile   源文件
     * @param targetDir 目标文件夹
     * @throws FileNotFoundException
     */
    public static void copyFileToDir(File srcFile, File targetDir) throws FileNotFoundException {
        String filePath = srcFile.getPath();
        if (!srcFile.exists()) {
            String msg = MessageFormat.format("File[{0}] not found", filePath);
            log.warn("FileUtil#copyFile, {}", msg);
            throw new FileNotFoundException(msg);
        }

        targetDir.mkdirs();
        try {
            Path srcPath = Paths.get(srcFile.getPath());
            Path dstPath = Paths.get(targetDir.getPath());
            java.nio.file.Files.copy(srcPath, dstPath.resolve(srcPath.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("FileUtil#copyFile error", e);
        }
    }

    /**
     * 将源文件夹的文件全部拷贝到目标文件夹下
     *
     * @param srcDir
     * @param dstDir
     * @throws IOException
     */
    public static void copyDirectory(String srcDir, String dstDir) {
        try {
            java.nio.file.Files.walk(Paths.get(srcDir))
                .forEach(source -> {
                    Path destination = Paths.get(dstDir, source.toString()
                        .substring(srcDir.length()));
                    try {
                        java.nio.file.Files.copy(source, destination,
                            StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        log.error("Copy file error", e);
                    }
                });
        } catch (IOException e) {
            log.error("FileUtil#copyDirectory error", e);
        }
    }

    /**
     * 将源文件夹的文件全部拷贝到目标文件夹下
     *
     * @param srcDir
     * @param dstDir
     * @throws IOException
     */
    public static void copyDirectory(File srcDir, File dstDir)
        throws FileNotFoundException, IOException {
        if (!srcDir.exists()) {
            log.error("FileUtil#copyDirectory source dir[{}] not exists", srcDir.getPath());
            throw new FileNotFoundException("Source dir[" + srcDir.getPath() + "] not exists");
        }

        mkdirsAddChmod777(dstDir);
        for (String f : srcDir.list()) {
            copyDirectoryCompatibilityMode(new File(srcDir, f), dstDir);
        }
    }

    private static void copyDirectoryCompatibilityMode(File srcFile, File dstFile)
        throws IOException {
        if (srcFile.isDirectory()) {
            copyDirectory(srcFile, dstFile);
        } else {
            copyFileToDir(srcFile, dstFile);
        }
    }

    /**
     * 增加权限, 使路径可上传文件
     */
    private static void addChmod777(File file) throws IOException {
        if (!isWindows()) {
            String cmdGrant = "chmod 777 " + file.getPath();
            log.info("FileUtilFile#addChmod777: augmentation after moving: {}", cmdGrant);
            Runtime.getRuntime().exec(cmdGrant);
        }
    }

    /**
     * 增加权限, 使路径及子路径都有权限
     */
    private static void addRChmod777(File file) throws IOException {
        if (!isWindows()) {
            String cmdGrant = "chmod -R 777 " + file.getAbsolutePath();
            log.info("FileUtilFile#addRChmod777: file augmentation after moving: {}", cmdGrant);
            Runtime.getRuntime().exec(cmdGrant);
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Win");
    }
}
