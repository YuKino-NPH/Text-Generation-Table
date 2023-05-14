package com.ruoyi.textgenerationtable.service;

import com.ruoyi.textgenerationtable.vo.FieldVo;

import java.util.List;


public interface IOptDatabaseService {
    void generateTable(String tableName, String primaryKey, List<FieldVo> dictList);
}
