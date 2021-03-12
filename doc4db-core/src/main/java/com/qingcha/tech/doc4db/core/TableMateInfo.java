package com.qingcha.tech.doc4db.core;

import java.util.List;

/**
 * @author qiqiang
 * @date 2020-09-24 11:01 上午
 */
public class TableMateInfo {
    private String tableName;
    private String tableComment = "";
    private List<TableLineInfo> tableLineInfoList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<TableLineInfo> getTableLineInfoList() {
        return tableLineInfoList;
    }

    public void setTableLineInfoList(List<TableLineInfo> tableLineInfoList) {
        this.tableLineInfoList = tableLineInfoList;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}