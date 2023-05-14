package com.ruoyi.textgenerationtable.mapper;

import java.util.List;

import com.ruoyi.textgenerationtable.domain.TableProperties;
import org.apache.ibatis.annotations.Mapper;

/**
 * 表结构管理Mapper接口
 *
 * @author ruoyi
 * @date 2023-05-14
 */
@Mapper
public interface TablePropertiesMapper {
    /**
     * 查询表结构管理
     *
     * @param id 表结构管理主键
     * @return 表结构管理
     */
    public TableProperties selectTablePropertiesById(Long id);

    /**
     * 查询表结构管理列表
     *
     * @param tableProperties 表结构管理
     * @return 表结构管理集合
     */
    public List<TableProperties> selectTablePropertiesList(TableProperties tableProperties);

    /**
     * 新增表结构管理
     *
     * @param tableProperties 表结构管理
     * @return 结果
     */
    public int insertTableProperties(TableProperties tableProperties);

    /**
     * 修改表结构管理
     *
     * @param tableProperties 表结构管理
     * @return 结果
     */
    public int updateTableProperties(TableProperties tableProperties);

    /**
     * 删除表结构管理
     *
     * @param id 表结构管理主键
     * @return 结果
     */
    public int deleteTablePropertiesById(Long id);

    /**
     * 批量删除表结构管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTablePropertiesByIds(Long[] ids);

    public List<TableProperties> mySelectTablePropertiesList(TableProperties tableProperties);
}
