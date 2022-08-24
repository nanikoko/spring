package com.jsp.command;

public class Criteria {
	private int page = 1; //페이지번호
	private int perPageNum = 10; //한페이지에 보여주는 행수
	private int startRowNum = 0; //첫번째 행수
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page<1) {
			this.page=1;
		}else {
			this.page=page;
		}
		setStartRowNum();
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum<1) {
			this.perPageNum=10;
		}else {
			this.perPageNum=perPageNum;
		}
		setStartRowNum();
	}
	public int getStartRowNum() {
		return startRowNum;
	}
	public void setStartRowNum() { 
		//첫번째 행수 구하는 공식
		this.startRowNum = (this.page-1)*perPageNum;
	}
	
	
}
