package com.ruoyi.textgenerationtable.utils;


import org.ansj.domain.Term;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class AnalysisDictUtils {
    public static void addWord(List<String> words) {
        if (CollectionUtils.isEmpty(words)) {
            return;
        }
        for (String word : words) {
            addWord(word);
        }
    }

    public static void addWord(String word) {
        if (StringUtils.isBlank(word)) {
            return;
        }
        UserDefineLibrary.insertWord(word);
    }

    public static void deleteWord(List<String> words) {
        if (CollectionUtils.isEmpty(words)) {
            return;
        }
        for (String word : words) {
            deleteWord(word);
        }
    }

    public static void deleteWord(String word) {
        if (StringUtils.isBlank(word)) {
            return;
        }
        UserDefineLibrary.removeWord(word);
    }

    public static List<Term> analysis(String text) {
        return DicAnalysis.parse(text).getTerms();
    }
}
