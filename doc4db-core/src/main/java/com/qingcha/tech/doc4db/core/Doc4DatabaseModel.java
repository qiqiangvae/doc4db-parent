package com.qingcha.tech.doc4db.core;

import java.util.List;

/**
 * @author qiqiang
 * @date 2020-10-09 5:25 下午
 */
public class Doc4DatabaseModel{
    private String databaseName;
    private String version;
    private List<TableMateInfo> tableMateInfoList;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<TableMateInfo> getTableMateInfoList() {
        return tableMateInfoList;
    }

    public void setTableMateInfoList(List<TableMateInfo> tableMateInfoList) {
        this.tableMateInfoList = tableMateInfoList;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}