package com.zheng.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.zheng.base.BaseStatistics;
import com.zheng.base.CharStatistics;
import com.zheng.base.LineStatistics;
import com.zheng.base.WordsStatistics;
import com.zheng.common.GetFilePath;
import com.zheng.common.GetInputData;
import com.zheng.expand.ExpandStatistics;
import com.zheng.swing.MainWindow;

public class WC {

	
	public static void main(String[] args) throws Exception {
		
		System.out.println("-c 返回文件 file.c 的字符数");
		System.out.println("-w 返回文件 file.c 的次的数");
		System.out.println("-l 返回文件 file.c 的行数");
		System.out.println("-s 递归处理目录下符合条件的文件");
		System.out.println("-a 返回更复杂的数据(代码行 / 空行 / 注释行)");
		System.out.println("-x 图形界面");
		System.out.println("-e 退出程序");
		
		GetFilePath gfp = new GetFilePath();
		
		GetInputData gid = new GetInputData();
		
		ExpandStatistics est = new ExpandStatistics();
		
		CharStatistics cst = new CharStatistics();
		
		LineStatistics lst = new LineStatistics();
		
		WordsStatistics wst = new WordsStatistics();
		
		BaseStatistics bst = new BaseStatistics();
		
		File file=new File("D:\\test.txt");
		
		if(!file.exists()) {
			
			try {
				
				file.createNewFile();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				System.out.println("文件创建失败了");
			}
		}
		
		String strAll = gid.getInputFile();
		
		String[] str = strAll.split(" ");
		
		String filePath = str[str.length - 1];
		
		String cmd = str[0];
		
		String[] filePathSplit = filePath.split("\\.");
		
		//获取.之后的后缀
		String filePathSplitAfter = filePathSplit[filePathSplit.length - 1];
		
		String[] filePathBefore = filePath.split("\\*");
	
		File fileReal = new File(filePath);
	
		
		if(filePathBefore.length > 1) {
			filePath = filePathBefore[0];
			bst.commonMethodMatch(gfp.getMatchFilePath(filePath,filePathSplitAfter));
		}
		
		
		switch(cmd) {
		case "-c": System.out.println("文件的字符数：" +  cst.charNum(fileReal));
			break;
		case "-w": System.out.println("文件的单词数：" + wst.wordsNum(fileReal));
			break;
		case "-l": System.out.println("文件的行数：" + lst.lineNum(fileReal));
			break;
		case "-s": gfp.printMatchFilePath(gfp.getMatchFilePath(filePath));
			break;
		case "-a": est.demoFileStatistics(filePath);
			break;
		case "-x":	MainWindow mw = new MainWindow();
			break;
		case "-e": 
			break;
			default: System.out.println("请输入正确的命令行");
				break;
		}
	}
}
