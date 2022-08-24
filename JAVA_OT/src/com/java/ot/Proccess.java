package com.java.ot;

public class Proccess {
	public String proccess(String input) {
		String str = "";
		
		if(input == null) return null;
		try {
			Integer.parseInt(input);
			str+="숫자:";
		}catch(Exception e){
			str+="문자:";
		}
		str += input;
		
		return str;
	}

}
