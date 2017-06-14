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
    private static String idFieldName;

    /**
     * 数据库名.表名
     */
    private static String tableName;

    /**
     * 字段名和字段值，json格式的字符串
     */
    private static String fieldMap;

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
            idFieldName = parse(prop.getProperty("id.field").trim());
            idStatus = parse(prop.getProperty("id.status").trim());
            fieldMap = parse(prop.getProperty("set.field.map").trim());
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



    public static String getIdFieldName() {
        return idFieldName;
    }

    public static void setIdFieldName(String idFieldName) {
        ExcelProperties.idFieldName = idFieldName;
    }

    public static String getFieldMap() {
        return fieldMap;
    }

    public static void setFieldMap(String fieldMap) {
        ExcelProperties.fieldMap = fieldMap;
    }

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableName) {
        ExcelProperties.tableName = tableName;
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
