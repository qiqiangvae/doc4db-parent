package com.qingcha.tech.doc4db.core;

import org.junit.Test;


/**
 * @author qiqiang
 * @date 2020-09-23 5:54 下午
 */
public class MainTest {
    public static final String url = "jdbc:mysql://mycloud:3307/soul?a=1&b=2";
    public static final String USER = "root";
    public static final String PASSWORD = "root";


    @Test
    public void mysqlTest() throws Exception {
        String url = "jdbc:mysql://mycloud:3307/soul?a=1&b=2";
        String USER = "root";
        String PASSWORD = "root";
        Doc4DatabaseConfiguration doc4DatabaseConfiguration = new Doc4DatabaseConfiguration(url, USER, PASSWORD);
        Doc4DatabaseGenerator doc4DatabaseGenerator = new Doc4DatabaseGenerator(doc4DatabaseConfiguration);
        doc4DatabaseGenerator.configFile(null, "target/demo.md");
        try {
            doc4DatabaseGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sqliteTest() throws Exception {
        String url = "jdbc:sqlite:/Users/qiqiang/workspace/code/python/chinese-poetry/ci/ci.db";
        Doc4DatabaseConfiguration doc4DatabaseConfiguration = new Doc4DatabaseConfiguration(url, USER, PASSWORD);
        Doc4DatabaseGenerator doc4DatabaseGenerator = new Doc4DatabaseGenerator(doc4DatabaseConfiguration);
        doc4DatabaseGenerator.configFile(null, "target/demo.md");
        try {
            doc4DatabaseGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void resourceTest() {
        System.out.println(this.getClass().getResource(""));
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("/"));
    }
}