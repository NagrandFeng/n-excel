package com.nagrand.excel.entity;

import java.util.List;
import java.util.Map;

/**
 * @author yeshufeng
 * @title
 * @date 2017/6/12
 */
public class SqlEntity {
    private String tableName;
    private String idFieldName;
    private List<Integer> idList;
    private Integer idStatus;
    private Map<String,String> fieldKVMap;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIdFieldName() {
        return idFieldName;
    }

    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Map<String, String> getFieldKVMap() {
        return fieldKVMap;
    }

    public void setFieldKVMap(Map<String, String> fieldKVMap) {
        this.fieldKVMap = fieldKVMap;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
