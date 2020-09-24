package com.qingcha.tech.doc4db.sample;

import com.qingcha.tech.doc4db.core.Doc4DatabaseConfiguration;
import com.qingcha.tech.doc4db.core.Doc4DatabaseGenerator;
import org.junit.Test;


/**
 * @author qiqiang
 * @date 2020-09-23 5:54 下午
 */
public class SampleMainTest {
    public static final String HOST = "localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "mypassword";
    public static final String DATABASE = "doc4db-demo";


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
}