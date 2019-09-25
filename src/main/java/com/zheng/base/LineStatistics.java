package com.zheng.base;

import java.io.File;
import java.util.List;

public class LineStatistics {

	BaseStatistics bst = new BaseStatistics();
	public int lineNum(File fileName) throws Exception {
		List<String> listStr = bst.commonMethod(fileName);
		int lineNum = 0;
		lineNum = listStr.size();
		return lineNum;
	}
}
