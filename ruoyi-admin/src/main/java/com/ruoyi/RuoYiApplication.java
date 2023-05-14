package com.ruoyi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.textgenerationtable.domain.DictProperties;
import com.ruoyi.textgenerationtable.enums.CommonStatus;
import com.ruoyi.textgenerationtable.service.IDictPropertiesService;
import com.ruoyi.textgenerationtable.utils.AnalysisDictUtils;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        ConfigurableApplicationContext application = SpringApplication.run(RuoYiApplication.class, args);
        // 初始化字典
        IDictPropertiesService dictPropertiesService = application.getBean(IDictPropertiesService.class);
        DictProperties dictProperties = new DictProperties();
        dictProperties.setStatus(CommonStatus.NORMAL.getCode());
        List<String> dict = dictPropertiesService.selectDictPropertiesList(dictProperties).stream().map(DictProperties::getDictName).collect(Collectors.toList());

        AnalysisDictUtils.addWord(dict);
        long l = System.currentTimeMillis();
        System.out.println("========加载字典========");
        AnalysisDictUtils.analysis("字典加载完成");
        System.out.println("=======字典加载完成======= 耗时："+(System.currentTimeMillis()-l)+"ms");
    }
}
