package com.zheng.base;

import java.io.File;
import java.util.List;

public class CharStatistics {
	
	BaseStatistics bst = new BaseStatistics();
	
	public int charNum(File fileName) throws Exception {
		
		List<String> listStr = bst.commonMethod(fileName);
		
		int charNum = 0;

		int len = listStr.size();
		
		for(int i = 0; i < len; i++) {
			
			String s = listStr.get(i);
			
			charNum += s.length();
			
		}
		
		return charNum;
		
	}
}
