package com.qingcha.tech.doc4db.core;

/**
 * @author qiqiang
 * @date 2020-09-23 7:05 下午
 */
public class TableLineInfo {
    private String field;
    private String type;
    private String none;
    private String key;
    private String defaultValue;
    private String comment;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNone() {
        return none;
    }

    public void setNone(String none) {
        this.none = none;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "{" +
                "field='" + field + '\'' +
                ", type='" + type + '\'' +
                ", none='" + none + '\'' +
                ", key='" + key + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", extra='" + comment + '\'' +
                '}';
    }
}