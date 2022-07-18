package org.sg.lf.sglogin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyFileUtil {
	public static boolean writeToLocal(String destination, InputStream input) {
		if(new File(destination).exists()==true) {
			return true;
		}
		try {
			int index;
			byte[] bytes = new byte[1024];
			FileOutputStream downloadFile = new FileOutputStream(destination);
			while ((index = input.read(bytes)) != -1) {
				downloadFile.write(bytes, 0, index);
            	downloadFile.flush();
			}
        input.close();
        downloadFile.close();
        return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
    }
	
	public static String title() {
		return "§f[§aSGLogin]§f";
	}
	
	//读取文本文件中的内容
	public static String readTextFileToString(InputStream readIn) {
		try {
			InputStreamReader read = new InputStreamReader(readIn, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String oneLine = null;
			StringBuffer buffer=new StringBuffer();
			while((oneLine= bufferedReader.readLine()) != null){
				buffer.append(oneLine);
			}
			read.close();
			return buffer.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	public static List<String> readTextFileToList(InputStream readIn) {
		try {
			InputStreamReader read = new InputStreamReader(readIn, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String oneLine = null;
			List<String> list=new ArrayList<String>();
			while((oneLine= bufferedReader.readLine()) != null){
				list.add(oneLine);
			}
			read.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
