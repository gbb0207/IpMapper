package com.xyf.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import com.xyf.builder.DatabaseReaderBuilder;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Ip转为地理位置工具类
 */
public class IpTransformAddressUtil {

    public static DatabaseReader reader;
    public static CityResponse response = null;

    static {
        reader = DatabaseReaderBuilder.getReader();
    }

    /**
     * 返回ip映射的所有结果
     * @param ip ip地址
     * @return  json结果集
     */
    public static CityResponse getResponse(String ip) throws IOException, GeoIp2Exception {
        InetAddress inetAddress = InetAddress.getByName(ip);
        return reader.city(inetAddress);   //获取所有结果
    }

    /**
     * 返回中文国家名称
     * @param ip ip地址
     * @return 中文国家名称
     */
    public static String getCountry(String ip) throws IOException, GeoIp2Exception {
        response = getResponse(ip);
        Country country = response.getCountry();
        return country.getNames().get("zh-CN");
    }

    /**
     * 返回中文省名称
     * @param ip ip地址
     * @return 中文省名称
     */
    public static String getProvince(String ip) throws IOException, GeoIp2Exception {
        response = getResponse(ip);
        Subdivision mostSpecificSubdivision = response.getMostSpecificSubdivision();
        return mostSpecificSubdivision.getNames().get("zh-CN");
    }

    /**
     * 返回中文城市名称
     * @param ip ip地址
     * @return 中文城市名称
     */
    public static String getCity(String ip) throws IOException, GeoIp2Exception {
        response = getResponse(ip);
        City city = response.getCity();
        return city.getNames().get("zh-CN");
    }

    /**
     * 返回拼接的地区信息
     * @param ip ip地址
     * @return 地区信息
     */
    public static String getArea(String ip) throws IOException, GeoIp2Exception {
        String empty = " ";
        return getCountry(ip) + empty + getProvince(ip) + empty + getCity(ip);
    }
}
