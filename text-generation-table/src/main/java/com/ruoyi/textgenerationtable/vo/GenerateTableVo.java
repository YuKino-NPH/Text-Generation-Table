package com.ruoyi.textgenerationtable.vo;

import lombok.Data;

import java.util.List;

@Data
public class GenerateTableVo {
    private String tableName;
    private String primaryKey;
    private String desc;
    private List<FieldVo> dictList;
}
