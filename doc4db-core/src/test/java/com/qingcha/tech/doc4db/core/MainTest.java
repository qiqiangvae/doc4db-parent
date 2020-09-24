package com.qingcha.tech.doc4db.core;

import org.junit.Test;


/**
 * @author qiqiang
 * @date 2020-09-23 5:54 下午
 */
public class MainTest {
    public static final String HOST = "localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static final String DATABASE = "database";


    @Test
    public void mainTest() throws Exception {
        Doc4DatabaseConfiguration doc4DatabaseConfiguration = new Doc4DatabaseConfiguration(HOST, USER, PASSWORD, DATABASE);
        Doc4DatabaseGenerator doc4DatabaseGenerator = new Doc4DatabaseGenerator(doc4DatabaseConfiguration);
        doc4DatabaseGenerator.setPrintLog(true);
        System.out.println(this.getClass().getClassLoader().getResource(""));
        doc4DatabaseConfiguration.setClassLoader(this.getClass().getClassLoader());
        doc4DatabaseConfiguration.setOutput("target/demo.md");
        doc4DatabaseGenerator.setPrintLog(true);
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