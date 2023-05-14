package com.ruoyi.textgenerationtable.enums;

public enum SysConfigEnum {
    DEFAULT_FIELD_LENGTH("default_field_length","默认字段长度"),
    DEFAULT_FIELD_TYPE("default_field_type","默认字段类型"),
    DEFAULT_FIELD_EMPTY("default_field_empty","默认是否可以为空"),
    FILTER_SYMBOLS("filter_symbols","过滤符号");
    private String code;
    private String desc;

    SysConfigEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
