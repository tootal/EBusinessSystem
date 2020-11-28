package com.demo.model;

import java.util.List;

/**
 * 实体类-分页
 * @author MouseHappy
 */
public class Page {
	
	private int totalPage; //记住总页数
	private int pageSize = 3; //页面大小
	private int totalRecord; //总记录数
	private int pageNum; //记住当前页
	private List list; //记住页面数据
	private int startPage; //起始页号
	private int endPage;
	private int startIndex; //记住用户想看的 页的数据从哪个地方开始取
	
	public Page(int pageNum, int totalrecord){
		this.pageNum = pageNum;
		this.totalRecord = totalrecord;
		//算出总页数
		this.totalPage = (this.totalRecord + this.pageSize - 1) / this.pageSize;
		//算出用户想看的页的数据从数据库哪个地方开始取
		this.startIndex = (this.pageNum - 1) * this.pageSize;
		
		if(this.totalPage <= 3){
			this.startPage = 1;
			this.endPage = this.totalPage;
		}else{
			this.startPage = pageNum - 1;
			this.endPage = pageNum + 1;
			
			if(this.startPage < 1){
				this.startPage = 1;
				this.endPage = 3;
			}
			if(this.endPage > this.totalPage){
				this.endPage = this.totalPage;
				this.startPage = this.totalPage - 2;
			}
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	
}
