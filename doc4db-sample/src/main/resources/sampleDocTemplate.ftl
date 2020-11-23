# ${databaseName!}自定义模版

## 数据库基本信息

## 数据表信息

<#list tableMateInfoList as tableMateInfo>
### ${tableMateInfo.tableName}
${tableMateInfo.tableComment}

|Field|Type|Null|Key|Comment|
|---|---|---|---|---|
<#list tableMateInfo.tableLineInfoList as info>
|${info.field!}|${info.type!}|${info.none!}|${info.key!}|${info.comment!}|
</#list>

</#list>
