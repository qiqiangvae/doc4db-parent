# ${databaseName!} ${version!}

## 数据库基本信息


## 数据表信息


<#list tableMateInfoList as tableMateInfo>
### ${tableMateInfo.tableName}

${tableMateInfo.tableComment}

#### 字段说明

|字段|类型|是否为空|索引|默认值|备注|
|---|---|---|---|---|---|
<#list tableMateInfo.tableLineInfoList as info>
|${info.field!}|${info.type!}|${info.none!}|${info.key!}|${info.defaultValue!}|${info.comment!}|
</#list>

</#list>
