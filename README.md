# n-excel简介

主要是对org.apache.poi的poi-ooxml进行了封装，用这个工具来读取Excel中的数据

## 使用的场景

因为工作中有这么一种操蛋的场景存在，因为程序的BUG，会出现线上几百条数据出现问题；解决的办法则是从线上的DBA系统导出出问题数据(一份excel文件 )，
然后只能使用类似下面的sql语句去修复线上的数据
```sql
update #{table_name} set #{set_map} where #{id} in ( #{id_list} )
```
如果导出的数据特别多的情况下，那个where id in (id1,id2,id3,id4...)会变得很长，手写sql会累死的。就搞了段代码来读Excel并输出sql

## excel文件说明

适用excel文件格式

excel下的sheet名称为tag1
excel的格式为两列
第一列是id，第二列是status

## 使用

classpath下的excel.properties中填好信息

```
#数据库名.表名
db.table=`db_name`.`table`
#表中的主键Id的字段名称
id.valid=order_id
#取excel status对应值的行
id.status=1
#valid：表中的字段名;value：对应的字段值
#json格式如 {"valid1":"value1","valid3":"value3","valid2":"value2","valid4":"value4"}
set.valid.map={"valid1":"value1","valid3":"value3","valid2":"value2","valid4":"value4"}
#excel文件的位置
file.url=E:\\jiaoyi\\sql_data.xlsx
```

然后
```java
ReadExcel readExcel = new ReadExcel();
readExcel.readeExcelGetSql();
```

console中会输出sql的信息

# 备注
code updating...
