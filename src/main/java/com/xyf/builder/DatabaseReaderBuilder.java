package com.xyf.builder;

import com.maxmind.geoip2.DatabaseReader;

import java.io.File;
import java.io.IOException;

public class DatabaseReaderBuilder {
    /**
     * GeoLite2数据库文件路径
     */
    public static final String DATABASE_FILE_PATH = "src/main/resources/GeoLite2-City_20240315/GeoLite2-City.mmdb";


    public static DatabaseReader getReader() {
        //读取 GeoLite2 数据库文件
        File database = new File(DATABASE_FILE_PATH);
        try {
            return new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
