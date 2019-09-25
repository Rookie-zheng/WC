package com.zheng.common;

import java.io.File;
import java.util.Scanner;

public class GetInputData {

	/**
	 * 获取输入的信息
	 */
	public String getInputFile() {
		String filePath = "";
		boolean isExist = true;
		
		
		String fileName = "";
		while (isExist == true) {
			System.out.println("请输入命令行： ");
			Scanner sc = new Scanner(System.in);
			filePath = sc.nextLine();
			String[] scStr = filePath.split(" ");
			fileName = scStr[scStr.length - 1];
			if(fileName.split("\\*").length > 1) {
				fileName = fileName.split("\\*")[0];
			}
			if(fileName.equals("-x")) {
				return fileName;
			}
			if(getInputTrue(fileName) == true) {
				isExist = false;
			}else {
				isExist = true;
				System.out.println("请输入正确的路径");
			}
		}
		
		return filePath;
	}

	/**
	 * 判断输入路径是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	private boolean getInputTrue(String filePath) {

		File file = new File(filePath);
		if (file.isDirectory() || file.isFile()) {
				return true;
		}
		
		return false;
	}
}
