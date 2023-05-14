package com.ruoyi.textgenerationtable.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.textgenerationtable.enums.CommonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.textgenerationtable.mapper.TablePropertiesMapper;
import com.ruoyi.textgenerationtable.domain.TableProperties;
import com.ruoyi.textgenerationtable.service.ITablePropertiesService;

/**
 * 表结构管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-14
 */
@Service
public class TablePropertiesServiceImpl implements ITablePropertiesService {
    @Autowired
    private TablePropertiesMapper tablePropertiesMapper;

    /**
     * 查询表结构管理
     *
     * @param id 表结构管理主键
     * @return 表结构管理
     */
    @Override
    public TableProperties selectTablePropertiesById(Long id) {
        return tablePropertiesMapper.selectTablePropertiesById(id);
    }

    /**
     * 查询表结构管理列表
     *
     * @param tableProperties 表结构管理
     * @return 表结构管理
     */
    @Override
    public List<TableProperties> selectTablePropertiesList(TableProperties tableProperties) {
        return tablePropertiesMapper.selectTablePropertiesList(tableProperties);
    }

    /**
     * 新增表结构管理
     *
     * @param tableProperties 表结构管理
     * @return 结果
     */
    @Override
    public int insertTableProperties(TableProperties tableProperties) {
        tableProperties.setCrtTime(new Date());
        tableProperties.setStatus(CommonStatus.NORMAL.getCode());
        tableProperties.setUpdTime(new Date());
        return tablePropertiesMapper.insertTableProperties(tableProperties);
    }

    /**
     * 修改表结构管理
     *
     * @param tableProperties 表结构管理
     * @return 结果
     */
    @Override
    public int updateTableProperties(TableProperties tableProperties) {
        tableProperties.setUpdTime(new Date());
        return tablePropertiesMapper.updateTableProperties(tableProperties);
    }

    /**
     * 批量删除表结构管理
     *
     * @param ids 需要删除的表结构管理主键
     * @return 结果
     */
    @Override
    public int deleteTablePropertiesByIds(Long[] ids) {
        return tablePropertiesMapper.deleteTablePropertiesByIds(ids);
    }

    /**
     * 删除表结构管理信息
     *
     * @param id 表结构管理主键
     * @return 结果
     */
    @Override
    public int deleteTablePropertiesById(Long id) {
        return tablePropertiesMapper.deleteTablePropertiesById(id);
    }

    @Override
    public List<TableProperties> mySelectTablePropertiesList(TableProperties tableProperties) {
        return tablePropertiesMapper.mySelectTablePropertiesList(tableProperties);
    }
}
