package com.ruoyi.textgenerationtable.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.textgenerationtable.mapper.FieldPropertiesMapper;
import com.ruoyi.textgenerationtable.domain.FieldProperties;
import com.ruoyi.textgenerationtable.service.IFieldPropertiesService;

/**
 * 字段管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-14
 */
@Service
public class FieldPropertiesServiceImpl implements IFieldPropertiesService {
    @Autowired
    private FieldPropertiesMapper fieldPropertiesMapper;

    /**
     * 查询字段管理
     *
     * @param id 字段管理主键
     * @return 字段管理
     */
    @Override
    public FieldProperties selectFieldPropertiesById(Long id) {
        return fieldPropertiesMapper.selectFieldPropertiesById(id);
    }

    /**
     * 查询字段管理列表
     *
     * @param fieldProperties 字段管理
     * @return 字段管理
     */
    @Override
    public List<FieldProperties> selectFieldPropertiesList(FieldProperties fieldProperties) {
        return fieldPropertiesMapper.selectFieldPropertiesList(fieldProperties);
    }

    /**
     * 新增字段管理
     *
     * @param fieldProperties 字段管理
     * @return 结果
     */
    @Override
    public int insertFieldProperties(FieldProperties fieldProperties) {
        fieldProperties.setCrtTime(new Date());
        fieldProperties.setUpdTime(new Date());
        return fieldPropertiesMapper.insertFieldProperties(fieldProperties);
    }

    /**
     * 修改字段管理
     *
     * @param fieldProperties 字段管理
     * @return 结果
     */
    @Override
    public int updateFieldProperties(FieldProperties fieldProperties) {
        return fieldPropertiesMapper.updateFieldProperties(fieldProperties);
    }

    /**
     * 批量删除字段管理
     *
     * @param ids 需要删除的字段管理主键
     * @return 结果
     */
    @Override
    public int deleteFieldPropertiesByIds(Long[] ids) {
        return fieldPropertiesMapper.deleteFieldPropertiesByIds(ids);
    }

    /**
     * 删除字段管理信息
     *
     * @param id 字段管理主键
     * @return 结果
     */
    @Override
    public int deleteFieldPropertiesById(Long id) {
        return fieldPropertiesMapper.deleteFieldPropertiesById(id);
    }
}
