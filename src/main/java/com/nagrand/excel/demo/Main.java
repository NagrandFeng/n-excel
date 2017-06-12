package com.nagrand.excel.demo;

import com.nagrand.excel.entity.ExcelProperties;
import com.nagrand.excel.tool.ReadExcel;

import java.io.File;

/**
 * @author yeshufeng
 * @title
 * @date 2017/6/12
 */
public class Main {
    static File file = new File(ExcelProperties.getFileUrl());

    public static void main(String[] args) {
        ReadExcel readExcel = new ReadExcel();
        readExcel.readeExcelGetSql(file);
    }
}
