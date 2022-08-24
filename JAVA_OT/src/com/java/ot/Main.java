package com.java.ot;

import java.util.Scanner;

public class Main {
	
	private Input inputIns = new Input();
	private Proccess proccessIns = new Proccess();
	private Output outputIns = new Output();

	public static void main(String[] args) {
		Main main = new Main();
		
		//입력
		//String input = main.input();
		String input = main.inputIns.input();
		
		//처리
		//String result = main.proccess(input);
		String result = main.proccessIns.proccess(input);

		//출력
		//main.output(result);
		main.outputIns.output(result);
	}
	

	private String input() {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		return input;
	}
	private String proccess(String input) {
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
	private void output(String str) {
		System.out.println(str);
	}
}
