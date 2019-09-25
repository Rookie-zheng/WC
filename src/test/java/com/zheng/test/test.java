package com.zheng.test;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.zheng.base.BaseStatistics;
import com.zheng.common.GetFilePath;
import com.zheng.common.GetInputData;
import com.zheng.expand.ExpandStatistics;
import com.zheng.swing.MainWindow;

public class test {

	
	BaseStatistics bst = new BaseStatistics();
	
	GetFilePath gfp = new GetFilePath();
	
	GetInputData gid = new GetInputData();
	
	ExpandStatistics est = new ExpandStatistics();
	
	@Test
	public void test() throws Exception {
		
		List<File> fileList = gfp.getMatchFilePath(gid.getInputFile());
		
		for(int i = 0 ; i < fileList.size(); i++) {
			System.out.println(fileList.get(i));
		}
	}
	
	@Test
	public void test01() throws Exception {
		est.demoFileStatistics(gid.getInputFile());
	}
	
	@Test
	public void test02() {
		MainWindow mw = new MainWindow();
	}
	
	
}
