package com.zheng.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseStatistics {

	public List<String> commonMethod(File fileName) throws Exception {

		InputStream is = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		List<String> listStr = new ArrayList<>();

		int i = 0;
		String s;
		while ( (s = br.readLine()) != null) {
			
			listStr.add(s);

		}
		br.close();
		is.close();
		return listStr;
	}

	/**
	 * ��������һ���ļ�����.c�ļ�����Ϣ
	 * 
	 * @param fileMacthName
	 * @throws Exception
	 */
	public void commonMethodMatch(List<File> fileMacthName) throws Exception {
		int charNum = 0;

		int lineNum = 0;

		int wordsNum = 0;

		for (File file : fileMacthName) {
			FileReader is = new FileReader(file);
			BufferedReader br = new BufferedReader(is);
			String s;
			while ((s = br.readLine()) != null) {
				charNum += s.length();
				lineNum++;
				 //����������ʽ��ͳ�Ƶ�����
	            Pattern p = Pattern.compile("\\b[\u4e00-\u9fa5_a-zA-Z]+\\b");
	            Matcher m = p.matcher(s);
	            System.out.println(s);
	            while(m.find()){
	            	wordsNum++;
	            };
			}
			br.close();
			is.close();
			System.out.println("�ļ���Ϊ�� " + file);
			System.out.println("�ַ���Ϊ�� " + charNum);
			System.out.println("������Ϊ�� " + wordsNum);
			System.out.println("����Ϊ�� " + lineNum);
		}
	}

}
