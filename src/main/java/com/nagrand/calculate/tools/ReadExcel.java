package com.nagrand.calculate.tools;

import com.google.common.collect.Lists;
import com.nagrand.calculate.entity.AsynOrder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Yeshufeng
 * @title
 * @date 2017/7/3
 */
public class ReadExcel {


    /**
     * 读取excel文档
     * @param file
     */
    public static void readExcel(File file){
        Map<Integer, Integer> idStatus = new HashMap<Integer, Integer>();
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Workbook workBook = new XSSFWorkbook(bis);
            Sheet sheet = workBook.getSheet("tag1");
            //获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            List<AsynOrder> orders = Lists.newArrayList();
            for (int i = firstRowNum; ; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();

                    AsynOrder asynOrder = new AsynOrder();
                    //每行一堆数据，只取第0和第14个数据
                    for (int j = firstCellNum; j < lastCellNum; j++) {
                        Cell cell = row.getCell(j);

                        Double value = cell.getNumericCellValue();
                        if(j==0){
                            //asyn_id
                            asynOrder.setOrderId(String.valueOf(value));

                        }else if(j==14){
                            //amount
                            asynOrder.setAmount(value);

                        }else{
                           //不做操作

                        }
                    }
                    orders.add(asynOrder);
                } else {
                    break;
                }
            }

            Double sumAmount=calcAmount(orders);
            System.out.println("总金额："+sumAmount);
        } catch (Exception e) {
            System.out.println("系统异常");
            System.out.println(e);
        }
    }

    public static Double calcAmount(List<AsynOrder> orderList) {
        Double result=0D;
        for (AsynOrder asynOrder : orderList) {
            result+=asynOrder.getAmount();
        }
        return result;
    }

}
