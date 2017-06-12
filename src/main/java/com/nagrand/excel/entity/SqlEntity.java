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
    private String idValidName;
    private List<Integer> idList;
    private Integer idStatus;
    private Map<String,String> validMap;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIdValidName() {
        return idValidName;
    }

    public void setIdValidName(String idValidName) {
        this.idValidName = idValidName;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Map<String, String> getValidMap() {
        return validMap;
    }

    public void setValidMap(Map<String, String> validMap) {
        this.validMap = validMap;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }
}
