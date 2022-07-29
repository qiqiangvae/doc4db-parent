<#assign underlineToHump = "com.qingcha.tech.doc4db.core.method.UnderlineToHumpMethod"?new() />
# ${databaseName!}自定义模版 ${version!}

## 数据库基本信息

## 数据表信息

<#list tableMateInfoList as tableMateInfo>
### ${tableMateInfo.tableName}
${tableMateInfo.tableComment}

#### title
```yaml
<#list tableMateInfo.tableLineInfoList as info>
---
key: ${underlineToHump(info.field!)}
title: ${info.comment!}
</#list>
```
#### searchBar

```yaml
id: ${tableMateInfo.tableName}
title: ${tableMateInfo.tableComment}
# 查询 bar
searchBar:
<#list tableMateInfo.tableLineInfoList as info>
  - label: ${info.comment!}
    type: Text
    param: ${underlineToHump(info.field!)}
</#list>
```
#### SQL
```xml
select
    <#list tableMateInfo.tableLineInfoList as info>
    ${info.field!} as ${underlineToHump(info.field!)}
    </#list>
from ${tableMateInfo.tableName}
<where>
<#list tableMateInfo.tableLineInfoList as info>
 <if test="${underlineToHump(info.field!)} != null ">
     and ${info.field!} = <#noparse>#{</#noparse>${underlineToHump(info.field!)}<#noparse>}</#noparse>
 </if>
</#list>
</where>
```

</#list>
