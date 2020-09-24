package com.qingcha.tech.doc4db.sample;

import com.qingcha.tech.doc4db.core.Doc4DatabaseGenerator;

/**
 * @author qiqiang
 * @date 2020-09-24 9:36 上午
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.getClass().getResource(""));
        System.out.println(test.getClass().getResource("/"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("sampleDocTemplate.ftl"));
        System.out.println(Doc4DatabaseGenerator.class.getClassLoader().getResource(""));
    }
}