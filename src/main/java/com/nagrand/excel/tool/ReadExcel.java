package com.nagrand.excel.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nagrand.excel.entity.ExcelProperties;
import com.nagrand.excel.entity.SqlEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * @author yeshufeng
 * @title
 * @date 2017/6/12
 */
public class ReadExcel {
//    static File file = new File("E:\\jiaoyi\\sql_data.xlsx");


    private SqlEntity sqlEntity;

    public ReadExcel() {
        this.sqlEntity = new SqlEntity();
    }

    public void readeExcelGetSql() {

        long start = System.currentTimeMillis();
        initSqlData();
        long end = System.currentTimeMillis();
        System.out.println("initSqlData 耗费:"+(end-start)+"ms");

        start = System.currentTimeMillis();
        File file = new File(ExcelProperties.getFileUrl());
        readExcel(file);
        end = System.currentTimeMillis();
        System.out.println("readExcel 耗费:"+(end-start)+"ms");

        start = System.currentTimeMillis();
        String sql = getSqlStr();
        end = System.currentTimeMillis();
        System.out.println("getSqlStr 耗费:"+(end-start)+"ms");

        System.out.println(sql);
    }

    private void initSqlData(){
        sqlEntity.setTableName(ExcelProperties.getTableName());
        sqlEntity.setIdValidName(ExcelProperties.getIdValidName());
        sqlEntity.setIdStatus(Integer.parseInt(ExcelProperties.getIdStatus()));
        String mapJson = ExcelProperties.getValidMap();
        Map<String,String> validMap = Maps.newHashMap();
        if(mapJson != null && !mapJson.equals("")){
           validMap = JSON.parseObject(mapJson,HashMap.class);
        }
        sqlEntity.setValidMap(validMap);

    }

    /**
     * 读取excel文档
     * @param file
     */
    private void readExcel(File file){
        Map<Integer, Integer> idStatus = new HashMap<Integer, Integer>();
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Workbook workBook = new XSSFWorkbook(bis);
            Sheet sheet = workBook.getSheet("tag1");
            //获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();

            for (int i = firstRowNum; ; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    //每行两个数据

                    Integer nowId = 0;
                    Integer nowStatus = 0;
                    for (int j = firstCellNum; j < lastCellNum; j++) {
                        Cell cell = row.getCell(j);
                        if (j == 0) {
                            nowId = (int) cell.getNumericCellValue();
                        } else if (j == 1) {
                            nowStatus = (int) cell.getNumericCellValue();
                        }
                    }
                    idStatus.put(nowId, nowStatus);
                } else {
                    break;
                }
            }
            getIdList(idStatus,sqlEntity.getIdStatus());
        } catch (Exception e) {
            System.out.println("系统异常");
            System.out.println(e);
        }
    }


    /**
     * 组装sql语句中条件子集的id列表
     * @param map
     * @param needStatus
     */
    private  void getIdList(Map<Integer, Integer> map, Integer needStatus) {
        List<Integer> idList = Lists.newArrayList();

        SortedMap<Integer,Integer> sort=new TreeMap<Integer,Integer>(map);
        Set<Map.Entry<Integer,Integer>> entry1=sort.entrySet();
        Iterator<Map.Entry<Integer,Integer>> it=entry1.iterator();

        while(it.hasNext()) {
            Map.Entry<Integer,Integer> entry=it.next();
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if(value.equals(needStatus)){
                idList.add(key);
            }
        }
        sqlEntity.setIdList(idList);
    }


    /**
     * 组装sql语句
     * @return
     */
    private String getSqlStr(){
        String sql = "update #{table_name} set #{set_map} where #{id} in ( #{id_list} )";
        String sqlTableName = sqlEntity.getTableName();
        String sqlSetMap = "";
        String sqlId = sqlEntity.getIdValidName();
        String sqlIdList = "";

        Map<String,String> setMap = sqlEntity.getValidMap();
        List<Integer> idList = sqlEntity.getIdList();

        for (Map.Entry<String, String> entry : setMap.entrySet()) {
            String valid = entry.getKey();
            String value = entry.getValue();
            sqlSetMap += valid+"="+value+",";
        }
        sqlSetMap = sqlSetMap.substring(0,sqlSetMap.length()-1);

        for (Integer id : idList){
            sqlIdList +=String.valueOf(id)+",";
        }
        sqlIdList = sqlIdList.substring(0,sqlIdList.length()-1);

        sql = sql.replace("#{table_name}",sqlTableName);
        sql = sql.replace("#{set_map}",sqlSetMap);
        sql = sql.replace("#{id}",sqlId);
        sql = sql.replace("#{id_list}",sqlIdList);

        return sql;
    }

}
