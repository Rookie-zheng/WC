package com.zheng.expand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import com.zheng.common.GetFilePath;
import com.zheng.common.GetInputData;

public class ExpandStatistics {

	GetFilePath gfp = new GetFilePath();

	GetInputData gid = new GetInputData();

	/**
	 * 输出 输入路径为文件的空行、代码行、注释行数目
	 * 
	 * @throws Exception
	 */
	public void demoFileStatistics(String filePath) throws Exception {

		InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));

		BufferedReader br = new BufferedReader(isr);

		int lineNotEmptyNum = 0;

		int lineEmptyNum = 0;

		int noteNum = 0;

		String s = null;
		
		while ((s = br.readLine()) != null) {

			String[] sSplit = s.split(" ");
			
			String sUnion = null ;
			
			for(int i = 0; i < sSplit.length; i++) {
				sUnion += sSplit[i];
			}
			
			char[] charStr = sUnion.toCharArray();

			if (s.length() > 1) {
				lineNotEmptyNum++;
			}

			if (s.length() <= 1) {
				lineEmptyNum++;
			}

			for (int i = 0; i < charStr.length - 1; i++) {
				if ((charStr[i]+"").equals("/")  && (charStr[i + 1]+"").equals("/")) {
					noteNum++;
				}
			}
		}

		System.out.println("代码行数为：" + lineNotEmptyNum);
		System.out.println("空格行数为：" + lineEmptyNum);
		System.out.println("注释行数为：" + noteNum);
	}
	
	public String demoFileStatistics1(String filePath) throws Exception {

		InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));

		BufferedReader br = new BufferedReader(isr);

		int lineNotEmptyNum = 0;

		int lineEmptyNum = 0;

		int noteNum = 0;

		String s = null;
		
		while ((s = br.readLine()) != null) {

			System.out.println(s);
			
			String[] sSplit = s.split(" ");
			
			String sUnion = "" ;
			
			for(int i = 0; i < sSplit.length; i++) {
				if(sSplit[i] != null)
				sUnion += sSplit[i];
			}
			char[] charStr = sUnion.toCharArray();

			if (sUnion.length() > 1) {
				lineNotEmptyNum++;
			}

			if (sUnion.length() <= 1) {
				lineEmptyNum++;
			}

			for (int i = 0; i < charStr.length - 1; i++) {
				if ((charStr[i]+"").equals("/")  && (charStr[i + 1]+"").equals("/")) {
					noteNum++;
				}
			}
		}
		return lineNotEmptyNum + " " + lineEmptyNum + " " + noteNum;
	}

	/**
	 * 输出 输入路径为文件夹目录的空行、代码行、注释行数目
	 * @param fileMacthName
	 * @throws Exception
	 */
	public void demoMatchStatistics(List<File> fileMacthName) throws Exception {
		
		int lineNotEmptyNum = 0;

		int lineEmptyNum = 0;

		int noteNum = 0;

		for (File file : fileMacthName) {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file));

			BufferedReader br = new BufferedReader(isr);

			while (br.read() != -1) {

				String s = br.readLine();
				
				String[] charStr = s.split(" ");

				if (charStr.length > 1) {
					lineNotEmptyNum++;
				}

				if (charStr.length <= 1) {
					lineEmptyNum++;
				}

				for (int i = 0; i < charStr.length - 1; i++) {
					if (charStr[i].toString().equals("/") && charStr[i + 1].toString().equals("/")) {
						noteNum++;
					}
				}
			}

			System.out.println("代码行数为：" + lineNotEmptyNum);
			System.out.println("空格行数为：" + lineEmptyNum);
			System.out.println("注释行数为：" + noteNum);
			}
		
		}
	
	}
