# ${databaseName!}

## 数据库基本信息

<br>

## 数据表信息

<br>

<#list tableMateInfoList as tableMateInfo>
### ${tableMateInfo.tableName}

<br>

${tableMateInfo.tableComment}

#### 字段说明

<br>

|字段|类型|是否为空|索引|默认值|备注|
|---|---|---|---|---|---|
<#list tableMateInfo.tableLineInfoList as info>
|${info.field!}|${info.type!}|${info.none!}|${info.key!}|${info.defaultValue!}|${info.comment!}|
</#list>

</#list>
