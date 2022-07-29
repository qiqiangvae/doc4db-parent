package com.qingcha.tech.doc4db.core;

/**
 * 配置
 *
 * @author qiqiang
 * @date 2020-09-23 5:52 下午
 */
public class Doc4DatabaseConfiguration {
    private String url;
    private String user;
    private String password;
    private String table;


    public Doc4DatabaseConfiguration(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}