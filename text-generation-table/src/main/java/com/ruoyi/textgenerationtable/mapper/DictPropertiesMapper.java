package com.ruoyi.textgenerationtable.mapper;

import java.util.List;

import com.ruoyi.textgenerationtable.domain.DictProperties;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典管理Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-14
 */
@Mapper
public interface DictPropertiesMapper {
    /**
     * 查询字典管理
     *
     * @param id 字典管理主键
     * @return 字典管理
     */
    public DictProperties selectDictPropertiesById(Long id);

    /**
     * 查询字典管理列表
     *
     * @param dictProperties 字典管理
     * @return 字典管理集合
     */
    public List<DictProperties> selectDictPropertiesList(DictProperties dictProperties);

    /**
     * 新增字典管理
     *
     * @param dictProperties 字典管理
     * @return 结果
     */
    public int insertDictProperties(DictProperties dictProperties);

    /**
     * 修改字典管理
     *
     * @param dictProperties 字典管理
     * @return 结果
     */
    public int updateDictProperties(DictProperties dictProperties);

    /**
     * 删除字典管理
     *
     * @param id 字典管理主键
     * @return 结果
     */
    public int deleteDictPropertiesById(Long id);

    /**
     * 批量删除字典管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDictPropertiesByIds(Long[] ids);

    List<DictProperties> selectDictPropertiesListByName(DictProperties dictProperties);
}
