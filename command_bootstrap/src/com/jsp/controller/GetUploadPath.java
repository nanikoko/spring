package com.jsp.controller;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;


public class GetUploadPath {
	private static Properties properties = new Properties();
	
	//properties객체화 과정 -> getProperty을 사용하여 key값을 가져오기 위해
	static {
		String resource = "com/jsp/properties/upload.properties";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUploadPath(String key) {
		String uploadPath = null;
		
		uploadPath = properties.getProperty(key);
		uploadPath = uploadPath.replace("/", File.separator);
		
		return uploadPath;
	}
}
