package com.jsp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

public class FileUploadResolver {
	public static List<File> fileUpload(FileItem[] items, String uploadPath) throws Exception  {
		
		List<File> uploadFileList = new ArrayList<File>(); //File은 파일의 정보 -> 파일이름 꺼내오기위해
		File file = new File(uploadPath);
		file.mkdirs();
		
		if(items != null)
			for(FileItem item : items) { //FileItem은 업로드한 파일
				String fileName = new File(item.getName()).getName(); //파일명(파일이름만 꺼내기 위해)
				fileName = MakeFileName.toUUIDFileName(fileName,"$$"); //고유파일명(중복없이)
				
				String filePath = uploadPath + File.separator + fileName;
				File storeFile = new File(filePath);
				
				//local HDD에 저장
				try {
					item.write(storeFile);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
				uploadFileList.add(storeFile);
			}
		return uploadFileList;
	}
}
