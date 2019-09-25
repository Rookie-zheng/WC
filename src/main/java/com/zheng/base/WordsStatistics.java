package com.zheng.base;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsStatistics {

	BaseStatistics bst = new BaseStatistics();
	
	public int wordsNum(File fileName) throws Exception {
		List<String> listStr = bst.commonMethod(fileName);
		int wordsNum = 0;
		int len = listStr.size();
		for(int i = 0; i < len; i++) {
			String s = listStr.get(i);
			 //����������ʽ��ͳ�Ƶ�����
            Pattern p = Pattern.compile("\\b[\u4e00-\u9fa5_a-zA-Z]+\\b");
            Matcher m = p.matcher(s);
            while(m.find()){
            	wordsNum++;
            };
		}
		return wordsNum;
	}
}
