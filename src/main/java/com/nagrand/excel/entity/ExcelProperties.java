package com.nagrand.excel.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author yeshufeng
 * @title
 * @date 2017/6/12
 */
public class ExcelProperties {

    private  static Log log = LogFactory.getLog(ExcelProperties.class);

    /**
     * 字段名称
     */
    private static String idValidName;

    /**
     * 数据库名.表名
     */
    private static String tableName;

    /**
     * 字段名和字段值，json格式的字符串
     */
    private static String validMap;

    /**
     * excel只取status=idStatus的数据
     */
    private static String idStatus;

    private static String fileUrl;
    static {
        Properties prop = new Properties();
        InputStream in = Thread.currentThread().getClass().getResourceAsStream("/excel.properties");
        try {
            prop.load(in);
            tableName = parse(prop.getProperty("db.table").trim());
            idValidName = parse(prop.getProperty("id.valid").trim());
            idStatus = parse(prop.getProperty("id.status").trim());
            validMap = parse(prop.getProperty("set.valid.map").trim());
            fileUrl = parse(prop.getProperty("file.url").trim());
        } catch (IOException e) {
            log.error("ExcelProperties初始化异常",e);
        }
    }

    private static String parse(String proVal) {
        if(proVal.startsWith("@{") && proVal.endsWith("}")){
            String envKey= proVal.substring("@{".length(), proVal.length()-1);
            return System.getenv(envKey);
        }
        return proVal;
    }


    public static void main(String[] args) {
        System.out.println(ExcelProperties.getValidMap());
    }

    public static String getIdValidName() {
        return idValidName;
    }

    public static void setIdValidName(String idValidName) {
        ExcelProperties.idValidName = idValidName;
    }

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableName) {
        ExcelProperties.tableName = tableName;
    }

    public static String getValidMap() {
        return validMap;
    }

    public static void setValidMap(String validMap) {
        ExcelProperties.validMap = validMap;
    }

    public static String getIdStatus() {
        return idStatus;
    }

    public static void setIdStatus(String idStatus) {
        ExcelProperties.idStatus = idStatus;
    }

    public static String getFileUrl() {
        return fileUrl;
    }

    public static void setFileUrl(String fileUrl) {
        ExcelProperties.fileUrl = fileUrl;
    }
}
