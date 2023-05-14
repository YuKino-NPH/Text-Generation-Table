package com.ruoyi.textgenerationtable.service;

import com.ruoyi.textgenerationtable.domain.DictProperties;
import com.ruoyi.textgenerationtable.domain.FieldProperties;
import com.ruoyi.textgenerationtable.vo.FieldVo;
import com.ruoyi.textgenerationtable.vo.GenerateTableVo;

import java.util.List;

public interface IAnalysisService {

    List<FieldVo> analysisTextGenerateField(String text);

    void generateTable(GenerateTableVo generateTableVo);

}
