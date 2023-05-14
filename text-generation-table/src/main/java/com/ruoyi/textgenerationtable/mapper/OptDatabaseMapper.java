package com.ruoyi.textgenerationtable.mapper;

import com.ruoyi.textgenerationtable.vo.FieldVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OptDatabaseMapper {
    public void createTable(@Param("tableName") String tableName, @Param("primaryKey") String primaryKey, @Param("fieldList") List<FieldVo> fieldList);
}
