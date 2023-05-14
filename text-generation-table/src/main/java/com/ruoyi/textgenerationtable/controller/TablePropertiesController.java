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
import com.ruoyi.textgenerationtable.domain.TableProperties;
import com.ruoyi.textgenerationtable.service.ITablePropertiesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 表结构管理Controller
 * 
 * @author ruoyi
 * @date 2023-05-14
 */
@RestController
@RequestMapping("/textgenerationtable/table")
public class TablePropertiesController extends BaseController
{
    @Autowired
    private ITablePropertiesService tablePropertiesService;

    /**
     * 查询表结构管理列表
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:table:list')")
    @GetMapping("/list")
    public TableDataInfo list(TableProperties tableProperties)
    {
        startPage();
        List<TableProperties> list = tablePropertiesService.selectTablePropertiesList(tableProperties);
        return getDataTable(list);
    }

    /**
     * 导出表结构管理列表
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:table:export')")
    @Log(title = "表结构管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TableProperties tableProperties)
    {
        List<TableProperties> list = tablePropertiesService.selectTablePropertiesList(tableProperties);
        ExcelUtil<TableProperties> util = new ExcelUtil<TableProperties>(TableProperties.class);
        util.exportExcel(response, list, "表结构管理数据");
    }

    /**
     * 获取表结构管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:table:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tablePropertiesService.selectTablePropertiesById(id));
    }

    /**
     * 新增表结构管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:table:add')")
    @Log(title = "表结构管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TableProperties tableProperties)
    {
        return toAjax(tablePropertiesService.insertTableProperties(tableProperties));
    }

    /**
     * 修改表结构管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:table:edit')")
    @Log(title = "表结构管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TableProperties tableProperties)
    {
        return toAjax(tablePropertiesService.updateTableProperties(tableProperties));
    }

    /**
     * 删除表结构管理
     */
    @PreAuthorize("@ss.hasPermi('textgenerationtable:table:remove')")
    @Log(title = "表结构管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tablePropertiesService.deleteTablePropertiesByIds(ids));
    }
}
