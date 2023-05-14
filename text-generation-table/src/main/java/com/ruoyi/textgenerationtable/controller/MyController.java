package com.ruoyi.textgenerationtable.controller;

import com.ruoyi.textgenerationtable.service.IOptDatabaseService;
import com.ruoyi.textgenerationtable.vo.GenerateTableVo;
import org.ansj.domain.Result;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my")
public class MyController {
    @Autowired
    IOptDatabaseService optDatabaseService;
    @GetMapping("/table")
    public String generateTable(String text){
        long startTime = System.currentTimeMillis();
        UserDefineLibrary.insertWord("番茄炒蛋");
        Result result = DicAnalysis.parse(text);
//        for (String s : forest.getParam()) {
//            System.out.println(s);
//        }

        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("基本分词: " + result + "(" + time + "ms)");

        result.getTerms().forEach(System.out::println);
        return result.toStringWithOutNature();
    }
    @PostMapping("/create")
    public String create(@RequestBody GenerateTableVo generateTableVo){
        optDatabaseService.generateTable(generateTableVo.getTableName(),generateTableVo.getPrimaryKey(),generateTableVo.getDictList());
        return "success";
    }
}
