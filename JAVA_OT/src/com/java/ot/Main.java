package com.java.ot;

import java.util.Scanner;

public class Main {
	
	private Input inputIns = new Input();
	private Proccess proccessIns = new Proccess();
	private Output outputIns = new Output();

	public static void main(String[] args) {
		Main main = new Main();
		
		//�Է�
		//String input = main.input();
		String input = main.inputIns.input();
		
		//ó��
		//String result = main.proccess(input);
		String result = main.proccessIns.proccess(input);

		//���
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
			str+="����:";
		}catch(Exception e){
			str+="����:";
		}
		str += input;
		
		return str;
	}
	private void output(String str) {
		System.out.println(str);
	}
}
