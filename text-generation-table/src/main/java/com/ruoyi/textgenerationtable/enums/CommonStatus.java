package com.ruoyi.textgenerationtable.enums;


public enum CommonStatus {
    NORMAL("0","正常"),
    CLOSE("1","关闭");
    private String code;
    private String desc;

    CommonStatus(String code, String desc) {
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
