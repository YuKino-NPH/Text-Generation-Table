package com.ruoyi.textgenerationtable.mapper;

import java.util.List;
import com.ruoyi.textgenerationtable.domain.FieldProperties;

/**
 * 字段管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-14
 */
public interface FieldPropertiesMapper 
{
    /**
     * 查询字段管理
     * 
     * @param id 字段管理主键
     * @return 字段管理
     */
    public FieldProperties selectFieldPropertiesById(Long id);

    /**
     * 查询字段管理列表
     * 
     * @param fieldProperties 字段管理
     * @return 字段管理集合
     */
    public List<FieldProperties> selectFieldPropertiesList(FieldProperties fieldProperties);

    /**
     * 新增字段管理
     * 
     * @param fieldProperties 字段管理
     * @return 结果
     */
    public int insertFieldProperties(FieldProperties fieldProperties);

    /**
     * 修改字段管理
     * 
     * @param fieldProperties 字段管理
     * @return 结果
     */
    public int updateFieldProperties(FieldProperties fieldProperties);

    /**
     * 删除字段管理
     * 
     * @param id 字段管理主键
     * @return 结果
     */
    public int deleteFieldPropertiesById(Long id);

    /**
     * 批量删除字段管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFieldPropertiesByIds(Long[] ids);
}
