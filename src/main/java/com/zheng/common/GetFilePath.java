package com.zheng.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetFilePath {
	
	GetInputData gid = new GetInputData();
	


	/**
	 * 获取一个文件夹目录下所有文件
	 */
	public List<File> getMatchFilePath(String filePath) {
		
		
		
		File file = new File(filePath);
		
		File[] fileList = file.listFiles();
		
		List<File> wjList = new ArrayList<>();
		
		if(fileList == null) {
			return null;
		}
		
		for(int i = 0; i < fileList.length; i++) {
			if(fileList[i].isFile()) {
				String[] getFileName = fileList[i].toString().split("\\.");
				String getLastName = getFileName[getFileName.length - 1];
//				String getFirstName = getFileName[0];
//				String[] getLcName = getFirstName.split("\\\\");
//				String getLastFileName = getLcName[getLcName.length - 1];
//				if(getLastFileName.equals("*")) {
//					if(getLastName.equals(""))
//				}
				wjList.add(fileList[i]);
			} else if(fileList[i].isDirectory()) {
				List<File> fileL = getMatchFilePath(fileList[i].getAbsolutePath());
				for(int j = 0; j < fileL.size(); j++) {
					wjList.add(fileL.get(j));
				}
			}
		}
		return wjList;
	}
	
public List<File> getMatchFilePath(String filePath,String fileAfter) {
		File file = new File(filePath);
		
		File[] fileList = file.listFiles();
		
		List<File> wjList = new ArrayList<>();
		
		if(fileList == null) {
			return null;
		}
		
		for(int i = 0; i < fileList.length; i++) {
			if(fileList[i].isFile()) {
				String[] getFileName = fileList[i].toString().split("\\.");
				String getLastName = getFileName[getFileName.length - 1];
				if(getLastName.equals(fileAfter)) {
					wjList.add(fileList[i]);
				}
			} else if(fileList[i].isDirectory()) {
				List<File> fileL = getMatchFilePath(fileList[i].getAbsolutePath());
				for(int j = 0; j < fileL.size(); j++) {
					wjList.add(fileL.get(j));
				}
			}
		}
		return wjList;
	}

	public void printMatchFilePath(List<File> fileListPath) {
		for(int i = 0; i < fileListPath.size(); i++) {
			File filePath = fileListPath.get(i);
			String filePathStr = filePath.toString();
			System.out.println(filePathStr);
		}
	}
	
	/**
	 * 获取文件
	 * @return
	 */
	public File getFilePath(String filePath) {
//		String[] getFileName = filePath.split("\\.");
//		String getLastName = getFileName[getFileName.length - 1];
//		if(getLastName.equals("c")) {
			return new File(filePath);
//		}
//	else {
//			System.out.println("请输入后缀为.c文件路径");
//			return getFilePath(gid.getInputFile());
//		}
	}
	
}
