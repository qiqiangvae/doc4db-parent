package com.qingcha.tech.doc4db.core;

import java.io.File;

/**
 * 配置
 *
 * @author qiqiang
 * @date 2020-09-23 5:52 下午
 */
public class Doc4DatabaseConfiguration {
    private String host;
    private String user;
    private String password;
    private String databaseName;
    private File templateFile;
    private String output;
    private ClassLoader classLoader;

    public Doc4DatabaseConfiguration(String host, String user, String password, String databaseName) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
        this.classLoader = Thread.currentThread().getContextClassLoader();
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public File getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(File templateFile) {
        this.templateFile = templateFile;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String toString() {
        return "Doc4DatabaseConfiguration{" +
                "host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", templateFile='" + templateFile + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}