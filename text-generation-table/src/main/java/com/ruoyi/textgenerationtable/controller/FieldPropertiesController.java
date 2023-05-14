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
import com.ruoyi.textgenerationtable.domain.FieldProperties;
import com.ruoyi.textgenerationtable.service.IFieldPropertiesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 字段管理Controller
 * 
 * @author ruoyi
 * @date 2023-05-14
 */
@RestController
@RequestMapping("/textgenerationtable/field")
public class FieldPropertiesController extends BaseController
{
    @Autowired
    private IFieldPropertiesService fieldPropertiesService;

    /**
     * 查询字段管理列表
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:field:list')")
    @GetMapping("/list")
    public TableDataInfo list(FieldProperties fieldProperties)
    {
        startPage();
        List<FieldProperties> list = fieldPropertiesService.selectFieldPropertiesList(fieldProperties);
        return getDataTable(list);
    }

    /**
     * 导出字段管理列表
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:field:export')")
    @Log(title = "字段管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FieldProperties fieldProperties)
    {
        List<FieldProperties> list = fieldPropertiesService.selectFieldPropertiesList(fieldProperties);
        ExcelUtil<FieldProperties> util = new ExcelUtil<FieldProperties>(FieldProperties.class);
        util.exportExcel(response, list, "字段管理数据");
    }

    /**
     * 获取字段管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:field:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fieldPropertiesService.selectFieldPropertiesById(id));
    }

    /**
     * 新增字段管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:field:add')")
    @Log(title = "字段管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FieldProperties fieldProperties)
    {
        return toAjax(fieldPropertiesService.insertFieldProperties(fieldProperties));
    }

    /**
     * 修改字段管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:field:edit')")
    @Log(title = "字段管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FieldProperties fieldProperties)
    {
        return toAjax(fieldPropertiesService.updateFieldProperties(fieldProperties));
    }

    /**
     * 删除字段管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:field:remove')")
    @Log(title = "字段管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fieldPropertiesService.deleteFieldPropertiesByIds(ids));
    }
}
