package com.ruoyi.textgenerationtable.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.textgenerationtable.domain.DictProperties;
import com.ruoyi.textgenerationtable.domain.FieldProperties;
import com.ruoyi.textgenerationtable.domain.TableProperties;
import com.ruoyi.textgenerationtable.enums.CommonStatus;
import com.ruoyi.textgenerationtable.enums.SysConfigEnum;
import com.ruoyi.textgenerationtable.service.*;
import com.ruoyi.textgenerationtable.utils.AnalysisDictUtils;
import com.ruoyi.textgenerationtable.vo.FieldVo;
import com.ruoyi.textgenerationtable.vo.GenerateTableVo;
import org.ansj.domain.Term;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements IAnalysisService {
    @Autowired
    ISysConfigService sysConfigService;
    @Autowired
    IDictPropertiesService dictPropertiesService;
    private Set<String> filterSymbolSet;
    @Autowired
    ITablePropertiesService tablePropertiesService;
    @Autowired
    IFieldPropertiesService fieldPropertiesService;
    @Autowired
    IOptDatabaseService optDatabaseService;
    @Override
    public List<FieldVo> analysisTextGenerateField(String text) {
        Set<String> filterSymbol = getFilterSymbolSet();
        // 1、解析文本
        List<Term> termList = AnalysisDictUtils.analysis(text);
        // 2、将文本组装成DictProperties
        List<FieldVo> fieldVoList = termList.stream().filter(e-> !filterSymbol.contains(e.getName())).map(e -> {
            String name = e.getName();
            FieldVo fieldVo = new FieldVo();
            fieldVo.setDictName(name);
            return fieldVo;
        }).collect(Collectors.toList());
        // 3、根据名称查询DictProperties字典中保存的数据对象
        for (FieldVo fieldVo : fieldVoList) {
            DictProperties dictProperties = new DictProperties();
            dictProperties.setDictName(fieldVo.getDictName());
            dictProperties.setStatus(CommonStatus.NORMAL.getCode());
            List<DictProperties> properties = dictPropertiesService.selectDictPropertiesListByName(dictProperties);
            if (!CollectionUtils.isEmpty(properties)){
                fieldVo.setDictValue(properties.get(0).getDictValue());
                fieldVo.setLength(properties.get(0).getLength());
                fieldVo.setType(properties.get(0).getType());
                fieldVo.setIsEmpty(properties.get(0).getIsEmpty());
            }else{
                fieldVo.setLength(Long.parseLong(sysConfigService.selectConfigByKey(SysConfigEnum.DEFAULT_FIELD_LENGTH.getCode())));
                fieldVo.setType(sysConfigService.selectConfigByKey(SysConfigEnum.DEFAULT_FIELD_TYPE.getCode()));
                fieldVo.setIsEmpty(Long.parseLong(sysConfigService.selectConfigByKey(SysConfigEnum.DEFAULT_FIELD_EMPTY.getCode())));
            }
            fieldVo.setDesc(fieldVo.getDictName());
        }
        return fieldVoList;
    }
    public Set<String> getFilterSymbolSet(){
        if (!CollectionUtils.isEmpty(filterSymbolSet)){
            return filterSymbolSet;
        }
        String text = sysConfigService.selectConfigByKey(SysConfigEnum.FILTER_SYMBOLS.getCode());
        JSONArray parse = JSON.parseArray(text);
        parse.getString(0);
        Set<String> filterSymbolSet=new HashSet<>();
        for (int i=0;i<parse.size();i++){
            filterSymbolSet.add(parse.getString(i));
        }
        this.filterSymbolSet=filterSymbolSet;
        return filterSymbolSet;
    }

    @Override
    @Transactional
    public void generateTable(GenerateTableVo generateTableVo) {
        verifyGenerateTableVo(generateTableVo);
        //1.创建TableProperties
        TableProperties tableProperties = new TableProperties();
        tableProperties.setDesc(generateTableVo.getDesc());
        tableProperties.setName(generateTableVo.getTableName());
        tableProperties.setTablePrimaryKey(generateTableVo.getPrimaryKey());
        tablePropertiesService.insertTableProperties(tableProperties);
        tableProperties = tablePropertiesService.mySelectTablePropertiesList(tableProperties).get(0);
        //2.创建FieldProperties
        for (FieldVo fieldVo:generateTableVo.getDictList()){
            FieldProperties fieldProperties = new FieldProperties();
            fieldProperties.setName(fieldVo.getDictValue());
            fieldProperties.setType(fieldVo.getType());
            fieldProperties.setDesc(fieldVo.getDesc());
            if (fieldVo.getLength()!=null&&fieldVo.getLength()!=0L){
                fieldProperties.setLength(fieldVo.getLength());
            }
            fieldProperties.setIsEmpty(fieldVo.getIsEmpty());
            fieldProperties.setTableId(tableProperties.getId());
            fieldProperties.setTableName(tableProperties.getName());
            fieldPropertiesService.insertFieldProperties(fieldProperties);
        }
        //3.调用Mybatis创建表
        optDatabaseService.generateTable(generateTableVo.getTableName(),generateTableVo.getPrimaryKey(),generateTableVo.getDictList());
    }

    private void verifyGenerateTableVo(GenerateTableVo generateTableVo) {
        if (generateTableVo==null){
            throw new RuntimeException("传入对象为空");
        }
        if (StringUtils.isBlank(generateTableVo.getTableName())||StringUtils.isBlank(generateTableVo.getPrimaryKey())||CollectionUtils.isEmpty(generateTableVo.getDictList())){
            throw new RuntimeException("传入参数缺失");
        }
        TableProperties tableProperties = new TableProperties();
        tableProperties.setName(generateTableVo.getTableName());
        List<TableProperties> tablePropertiesList = tablePropertiesService.mySelectTablePropertiesList(tableProperties);
        if (!CollectionUtils.isEmpty(tablePropertiesList)){
            throw new RuntimeException("该表名已存在");
        }
        Set<String> tableNameSet = generateTableVo.getDictList().stream().map(FieldVo::getDictValue).collect(Collectors.toSet());
        if (!tableNameSet.contains(generateTableVo.getPrimaryKey())){
            throw new RuntimeException("主键输入错误，不存在");
        }
    }
}
