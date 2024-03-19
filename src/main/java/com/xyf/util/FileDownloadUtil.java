package com.xyf.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadUtil {

    private static final String TARGET_LIB = "./cache/files/";
    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * 文件下载到本地
     *
     * @param fileUrl    文件url
     * @param targetName 自定义本地文件名
     * @return 下载的文件名
     */
    public static String FileDownload(String fileUrl, String targetName) throws IOException {
        String TARGET_PATH = TARGET_LIB + targetName;   //实际本地路径 = 目录 + 传入自定义文件名

        ResponseEntity<byte[]> response = restTemplate.getForEntity(fileUrl, byte[].class);
        HttpStatus statusCode = response.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            byte[] pdfBytes = response.getBody();
            if (pdfBytes != null) {
                try {
                    Path path = Paths.get(TARGET_PATH); //本地文件路径
                    Files.createDirectories(path.getParent()); // 确保父目录存在
                    Files.write(path, pdfBytes);    //将文件内容写入本地
                    return new File(fileUrl).getName();
                } catch (IOException e) {
                    throw new IOException("保存PDF文件时出错：" + e.getMessage());
                }
            } else {
                throw new IOException("接收到空的PDF内容。");
            }
        } else {
            throw new HttpClientErrorException(statusCode);
        }
    }
}
