package com.qingcha.tech.doc4db.core.worker;

import com.qingcha.tech.doc4db.core.Doc4DatabaseConfiguration;
import com.qingcha.tech.doc4db.core.Doc4DatabaseModel;
import com.qingcha.tech.doc4db.core.TableLineInfo;
import com.qingcha.tech.doc4db.core.TableMateInfo;

import java.net.URI;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiqiang
 */
@WorkerSchema("sqlite")
public class SqliteGenerateWorker extends GenerateWorker {
    private String database;
    private Connection connection;


    public SqliteGenerateWorker(Doc4DatabaseConfiguration doc4DatabaseConfiguration) {
        super(doc4DatabaseConfiguration);
    }

    @Override
    public Doc4DatabaseModel create() throws Exception {
        connection = getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        List<TableMateInfo> tables = getTables(metaData);
        for (TableMateInfo table : tables) {
            List<TableLineInfo> tableLineInfoList = getTableLineInfoData(metaData, table.getTableName());
            table.setTableLineInfoList(tableLineInfoList);
        }
        Doc4DatabaseModel templateModel = new Doc4DatabaseModel();
        templateModel.setDatabaseName(database);
        templateModel.setTableMateInfoList(tables);
        String version = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        templateModel.setVersion(version);
        return templateModel;
    }


    private List<TableLineInfo> getTableLineInfoData(DatabaseMetaData metaData, String tableName) throws ClassNotFoundException, SQLException {
        List<TableLineInfo> tableLineInfoList = new ArrayList<>();
        //3.操作数据库，实现增删改查
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("PRAGMA table_info('%s')", tableName));
        while (rs.next()) {
            String field = rs.getString("name");
            String type = rs.getString("type");
            String none = rs.getString("notnull");
            String key = rs.getString("pk");
            String defaultValue = rs.getString("dflt_value");
            TableLineInfo tableLineInfo = new TableLineInfo();
            tableLineInfo.setField(field);
            tableLineInfo.setType(type);
            tableLineInfo.setNone(none);
            tableLineInfo.setKey(key);
            tableLineInfo.setDefaultValue(defaultValue);
            tableLineInfoList.add(tableLineInfo);
        }
        return tableLineInfoList;
    }

    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = getDoc4DatabaseConfiguration().getUrl();
        String user = getDoc4DatabaseConfiguration().getUser();
        String password = getDoc4DatabaseConfiguration().getPassword();
        URI uri = URI.create(url.substring(5));
        this.database = uri.getPath().substring(1);
        //1.加载驱动程序
        Class.forName("org.sqlite.JDBC");
        //2. 获得数据库连接
        return DriverManager.getConnection(url, user, password);
    }


    private List<TableMateInfo> getTables(DatabaseMetaData metaData) throws SQLException {
        ArrayList<TableMateInfo> tableMateInfoList = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM sqlite_master where type='table'");
        while (resultSet.next()) {
            String tableName = resultSet.getString("tbl_name");
            TableMateInfo tableMateInfo = new TableMateInfo();
            tableMateInfo.setTableName(tableName);
            tableMateInfoList.add(tableMateInfo);
        }
        return tableMateInfoList;
    }

}