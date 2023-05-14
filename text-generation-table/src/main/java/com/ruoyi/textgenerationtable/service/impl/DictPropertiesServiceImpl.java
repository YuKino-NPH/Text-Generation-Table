package com.ruoyi.textgenerationtable.service.impl;

import com.ruoyi.textgenerationtable.domain.DictProperties;
import com.ruoyi.textgenerationtable.enums.CommonStatus;
import com.ruoyi.textgenerationtable.mapper.DictPropertiesMapper;
import com.ruoyi.textgenerationtable.service.IDictPropertiesService;
import com.ruoyi.textgenerationtable.utils.AnalysisDictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 字典管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-05-14
 */
@Service
public class DictPropertiesServiceImpl implements IDictPropertiesService {
    @Autowired
    private DictPropertiesMapper dictPropertiesMapper;

    /**
     * 查询字典管理
     *
     * @param id 字典管理主键
     * @return 字典管理
     */
    @Override
    public DictProperties selectDictPropertiesById(Long id) {
        return dictPropertiesMapper.selectDictPropertiesById(id);
    }

    /**
     * 查询字典管理列表
     *
     * @param dictProperties 字典管理
     * @return 字典管理
     */
    @Override
    public List<DictProperties> selectDictPropertiesList(DictProperties dictProperties) {
        return dictPropertiesMapper.selectDictPropertiesList(dictProperties);
    }

    /**
     * 新增字典管理
     *
     * @param dictProperties 字典管理
     * @return 结果
     */
    @Override
    public int insertDictProperties(DictProperties dictProperties) {
        dictProperties.setCrtTime(new Date());
        dictProperties.setStatus(CommonStatus.NORMAL.getCode());
        dictProperties.setUpdTime(new Date());
        // 添加进内存词典里
        AnalysisDictUtils.addWord(dictProperties.getDictValue());
        return dictPropertiesMapper.insertDictProperties(dictProperties);
    }

    /**
     * 修改字典管理
     *
     * @param dictProperties 字典管理
     * @return 结果
     */
    @Override
    public int updateDictProperties(DictProperties dictProperties) {
        dictProperties.setUpdTime(new Date());
        return dictPropertiesMapper.updateDictProperties(dictProperties);
    }

    /**
     * 批量删除字典管理
     *
     * @param ids 需要删除的字典管理主键
     * @return 结果
     */
    @Override
    public int deleteDictPropertiesByIds(Long[] ids) {
        List<String> words = new ArrayList<>();
        for (Long id : ids) {
            DictProperties dictProperties = selectDictPropertiesById(id);
            if (dictProperties != null && dictProperties.getStatus().equals(CommonStatus.NORMAL.getCode())) {
                words.add(dictProperties.getDictName());
            }
        }
        AnalysisDictUtils.deleteWord(words);
        return dictPropertiesMapper.deleteDictPropertiesByIds(ids);
    }

    /**
     * 删除字典管理信息
     *
     * @param id 字典管理主键
     * @return 结果
     */
    @Override
    public int deleteDictPropertiesById(Long id) {
        DictProperties dictProperties = selectDictPropertiesById(id);
        if (dictProperties != null && dictProperties.getStatus().equals(CommonStatus.NORMAL.getCode())) {
            AnalysisDictUtils.deleteWord(dictProperties.getDictName());
        }
        return dictPropertiesMapper.deleteDictPropertiesById(id);
    }

    @Override
    public List<DictProperties> selectDictPropertiesListByName(DictProperties dictProperties) {
        return dictPropertiesMapper.selectDictPropertiesListByName(dictProperties);
    }
}
