package com.ruoyi.textgenerationtable.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.textgenerationtable.domain.DictProperties;
import com.ruoyi.textgenerationtable.service.IDictPropertiesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 字典管理Controller
 *
 * @author ruoyi
 * @date 2023-05-14
 */
@RestController
@RequestMapping("/textgenerationtable/dict")
public class DictPropertiesController extends BaseController {
    @Autowired
    private IDictPropertiesService dictPropertiesService;

    /**
     * 查询字典管理列表
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(DictProperties dictProperties) {
        startPage();
        List<DictProperties> list = dictPropertiesService.selectDictPropertiesList(dictProperties);
        return getDataTable(list);
    }

    /**
     * 导出字典管理列表
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:dict:export')")
    @Log(title = "字典管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DictProperties dictProperties) {
        List<DictProperties> list = dictPropertiesService.selectDictPropertiesList(dictProperties);
        ExcelUtil<DictProperties> util = new ExcelUtil<DictProperties>(DictProperties.class);
        util.exportExcel(response, list, "字典管理数据");
    }

    /**
     * 获取字典管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:dict:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(dictPropertiesService.selectDictPropertiesById(id));
    }

    /**
     * 新增字典管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:dict:add')")
    @Log(title = "字典管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DictProperties dictProperties) {
        return toAjax(dictPropertiesService.insertDictProperties(dictProperties));
    }

    /**
     * 修改字典管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:dict:edit')")
    @Log(title = "字典管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DictProperties dictProperties) {
        return toAjax(dictPropertiesService.updateDictProperties(dictProperties));
    }

    /**
     * 删除字典管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:dict:remove')")
    @Log(title = "字典管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(dictPropertiesService.deleteDictPropertiesByIds(ids));
    }
}
