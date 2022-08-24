package com.jsp.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.exception.NotMultipartFormDataException;

public class XSSMutipartHttpServletRequestparser extends MultipartHttpServletRequestParser{
	public XSSMutipartHttpServletRequestparser(HttpServletRequest request, int memory_threshold, int max_file_size, int max_request_size) throws NotMultipartFormDataException, UnsupportedEncodingException, FileUploadException{
		super(request,memory_threshold,max_file_size,max_request_size);
	}
	
	public String getXSSParameter(String paramName) {
		String result = HTMLInputFilter.htmlSpecialChars(super.getParameter(paramName));
		return result;
	}
	public String[] getXSSParameterValue(String paramName) {
		String[] paramValue = new String[super.getParameterValues(paramName).length];
		for(int i=0; i<paramValue.length; i++) {
			paramValue[i]=HTMLInputFilter.htmlSpecialChars(super.getParameterValues(paramName)[i]);
		}
		return paramValue;
	}
}
