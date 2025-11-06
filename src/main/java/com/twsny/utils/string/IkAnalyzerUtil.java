package com.twsny.utils.string;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 分词工具
 */
public class IkAnalyzerUtil {
    public static String[] splitWords(String content) {
        List<String> splitWords = new ArrayList<>();
        try (StringReader reader = new StringReader(content)) {
            IKSegmenter ikSegmenter = new IKSegmenter(reader, true);
            Lexeme lexeme;
            while ((lexeme = ikSegmenter.next()) != null) {
                splitWords.add(lexeme.getLexemeText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splitWords.toArray(new String[splitWords.size()]);
    }

    public static void analysis(String content) {
        try {
            String[] splitWords = IkAnalyzerUtil.splitWords(content);
            Arrays.stream(splitWords).forEach(s -> {
                System.out.println(s);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("===========");
        String text = "我喜欢使用IK分词器进行中文分词。";
        IkAnalyzerUtil.analysis(text);
        text = "广东省广州市黄埔区九龙镇镇龙村丰彩街19号广州市第二老人院祥晖楼架空层近饭堂处消防箱旁";
        IkAnalyzerUtil.analysis(text);
        text = "西三环北路105号首都师范大学1号教学楼一楼大厅";
        IkAnalyzerUtil.analysis(text);
        System.out.println("");
        text = "江苏省苏州市吴中区苏州新闻大厦1F北前台";
        IkAnalyzerUtil.analysis(text);
        text = "北京市北京市东城区前门东大街地铁8号线前门线站台";
        IkAnalyzerUtil.analysis(text);
        text = "陕西省西安市雁塔区丈八东路311号跳水馆";
        IkAnalyzerUtil.analysis(text);
        text = "江苏省苏州市苏州工业园区斜塘星湖街200号(星湖街与机场路交叉口)";
        IkAnalyzerUtil.analysis(text);
        text = " 苏州工业园区南京航空航天大学苏州附属中学（星湖街校区）一楼总务处对面";
        IkAnalyzerUtil.analysis(text);
    }
}
