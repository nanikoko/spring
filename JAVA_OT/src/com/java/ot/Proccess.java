package com.java.ot;

public class Proccess {
	public String proccess(String input) {
		String str = "";
		
		if(input == null) return null;
		try {
			Integer.parseInt(input);
			str+="����:";
		}catch(Exception e){
			str+="����:";
		}
		str += input;
		
		return str;
	}

}
