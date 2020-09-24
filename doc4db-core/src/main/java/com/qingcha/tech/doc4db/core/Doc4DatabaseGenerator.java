package com.qingcha.tech.doc4db.core;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiqiang
 * @date 2020-09-23 7:11 下午
 */
public class Doc4DatabaseGenerator implements Generator {
    private final Logger logger = LoggerFactory.getLogger(Doc4DatabaseGenerator.class);
    private final Doc4DatabaseConfiguration doc4DatabaseConfiguration;
    private boolean printLog;


    public Doc4DatabaseGenerator(Doc4DatabaseConfiguration doc4DatabaseConfiguration) {
        this.doc4DatabaseConfiguration = doc4DatabaseConfiguration;
        System.out.println(this.doc4DatabaseConfiguration.toString());
    }


    @Override
    public void generate() throws Exception {
        Connection connection = getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        List<TableMateInfo> tables = getTables(metaData);
        for (TableMateInfo table : tables) {
            List<TableLineInfo> tableLineInfoList = getTableLineInfoData(connection, table.getTableName());
            table.setTableLineInfoList(tableLineInfoList);
        }
        outputDoc(metaData, tables);
    }

    private List<TableMateInfo> getTables(DatabaseMetaData metaData) throws SQLException {
        ArrayList<TableMateInfo> tableMateInfoList = new ArrayList<>();
        ResultSet tableSet = metaData.getTables("health_catalog", "%", "%", new String[]{"TABLE"});
        while (tableSet.next()) {
            String tableName = tableSet.getString("TABLE_NAME");
            String tableComment = tableSet.getString("REMARKS");
            TableMateInfo tableMateInfo = new TableMateInfo();
            tableMateInfo.setTableName(tableName);
            tableMateInfo.setTableComment(tableComment);
            tableMateInfoList.add(tableMateInfo);
        }
        return tableMateInfoList;
    }

    private void outputDoc(DatabaseMetaData metaData, List<TableMateInfo> tableMateInfoList) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("utf-8");
        File templateFile = doc4DatabaseConfiguration.getTemplateFile();
        Template template;
        if (templateFile == null) {
            configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "");
            template = configuration.getTemplate("defaultDocTemplate.ftl");
            logger.info("使用默认模版 defaultDocTemplate.ftl");
        } else {
            configuration.setTemplateLoader(new FileTemplateLoader(new File(templateFile.getParent())));
            template = configuration.getTemplate(templateFile.getName());
            logger.info("templateFile:[{}]", templateFile);
        }
        //创建一个Writer对象，指定生成的文件保存的路径及文件名。
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("databaseName", doc4DatabaseConfiguration.getDatabaseName());
        templateModel.put("tableMateInfoList", tableMateInfoList);
        Writer out = new FileWriter(new File(doc4DatabaseConfiguration.getOutput()));
        //调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
        template.process(templateModel, out);
        //关闭writer对象。
        out.flush();
        out.close();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        String host = doc4DatabaseConfiguration.getHost();
        String user = doc4DatabaseConfiguration.getUser();
        String password = doc4DatabaseConfiguration.getPassword();
        String databaseName = doc4DatabaseConfiguration.getDatabaseName();
        //1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 获得数据库连接
        String url = "jdbc:mysql://" + host + "/" + databaseName;
        return DriverManager.getConnection(url, user, password);
    }

    private List<TableLineInfo> getTableLineInfoData(Connection conn, String tableName) throws ClassNotFoundException, SQLException {
        List<TableLineInfo> tableLineInfoList = new ArrayList<>();
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show columns from " + tableName);
        while (rs.next()) {
            String field = rs.getString("Field");
            String type = rs.getString("Type");
            String none = rs.getString("Null");
            String key = rs.getString("Key");
            String defaultValue = rs.getString("Default");
            String extra = rs.getString("Extra");
            TableLineInfo tableLineInfo = new TableLineInfo();
            tableLineInfo.setField(field);
            tableLineInfo.setType(type);
            tableLineInfo.setNone(none);
            tableLineInfo.setKey(key);
            tableLineInfo.setDefaultValue(defaultValue);
            tableLineInfo.setExtra(extra);
            tableLineInfoList.add(tableLineInfo);
        }
        return tableLineInfoList;
    }

    public void setPrintLog(boolean printLog) {
        this.printLog = printLog;
    }
}