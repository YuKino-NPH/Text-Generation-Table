package com.ruoyi.textgenerationtable.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.textgenerationtable.service.IAnalysisService;
import com.ruoyi.textgenerationtable.vo.FieldVo;
import com.ruoyi.textgenerationtable.vo.GenerateTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/textgenerationtable/analysis")
public class AnalysisController extends BaseController {
    @Autowired
    IAnalysisService analysisService;
    @PostMapping()
    @PreAuthorize("@ss.hasPermi('textgenerationtable:analysis:analysis')")
    public AjaxResult analysisText(@RequestBody String text){
        List<FieldVo> fieldVoList=analysisService.analysisTextGenerateField(text);
        return success(fieldVoList);
    }
    @PostMapping("/createTable")
    @PreAuthorize("@ss.hasPermi('textgenerationtable:analysis:create')")
    public AjaxResult createTable(@RequestBody GenerateTableVo generateTableVo){
        analysisService.generateTable(generateTableVo);
        return success();
    }

}
