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
		
		System.out.println("-c �����ļ� file.c ���ַ���");
		System.out.println("-w �����ļ� file.c �Ĵε���");
		System.out.println("-l �����ļ� file.c ������");
		System.out.println("-s �ݹ鴦��Ŀ¼�·����������ļ�");
		System.out.println("-a ���ظ����ӵ�����(������ / ���� / ע����)");
		System.out.println("-x ͼ�ν���");
		System.out.println("-e �˳�����");
		
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
				
				System.out.println("�ļ�����ʧ����");
			}
		}
		
		String strAll = gid.getInputFile();
		
		String[] str = strAll.split(" ");
		
		String filePath = str[str.length - 1];
		
		String cmd = str[0];
		
		String[] filePathSplit = filePath.split("\\.");
		
		//��ȡ.֮��ĺ�׺
		String filePathSplitAfter = filePathSplit[filePathSplit.length - 1];
		
		String[] filePathBefore = filePath.split("\\*");
	
		File fileReal = new File(filePath);
	
		
		if(filePathBefore.length > 1) {
			filePath = filePathBefore[0];
			bst.commonMethodMatch(gfp.getMatchFilePath(filePath,filePathSplitAfter));
		}
		
		
		switch(cmd) {
		case "-c": System.out.println("�ļ����ַ�����" +  cst.charNum(fileReal));
			break;
		case "-w": System.out.println("�ļ��ĵ�������" + wst.wordsNum(fileReal));
			break;
		case "-l": System.out.println("�ļ���������" + lst.lineNum(fileReal));
			break;
		case "-s": gfp.printMatchFilePath(gfp.getMatchFilePath(filePath));
			break;
		case "-a": est.demoFileStatistics(filePath);
			break;
		case "-x":	MainWindow mw = new MainWindow();
			break;
		case "-e": 
			break;
			default: System.out.println("��������ȷ��������");
				break;
		}
	}
}
