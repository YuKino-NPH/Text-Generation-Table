package com.ruoyi.textgenerationtable.service.impl;

import com.ruoyi.textgenerationtable.mapper.OptDatabaseMapper;
import com.ruoyi.textgenerationtable.service.IOptDatabaseService;
import com.ruoyi.textgenerationtable.vo.FieldVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptDataBaseServiceImpl implements IOptDatabaseService {
    @Autowired
    OptDatabaseMapper optDatabaseMapper;
    @Override
    public void generateTable(String tableName, String primaryKey, List<FieldVo> dictList) {
        optDatabaseMapper.createTable(tableName,primaryKey,dictList);
    }
}
