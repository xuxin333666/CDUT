package cn.kgc.bean;

import java.util.List;

public class PageBeanDto<T> {
	private List<T> dataList;
	private int countPerPage = 5;
	private int totalNumberOfPages;
	private int currentPage;
	
	
	public PageBeanDto(int dataCount) {
		if(dataCount % countPerPage == 0) {
			this.totalNumberOfPages = dataCount / countPerPage;
		} else {
			this.totalNumberOfPages = dataCount / countPerPage + 1;
		}
	}

	
	public List<T> getDataList() {
		return dataList;
	}


	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}


	public int getCountPerPage() {
		return countPerPage;
	}


	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}


	public int getCurrentPage() {
		if(currentPage < 1) {
			currentPage = 1;
			return 1;
		}
		if(currentPage > totalNumberOfPages) {
			currentPage = totalNumberOfPages;
			return totalNumberOfPages;
		}
		return currentPage;
	}


	public void setCurrentPage(int currentPage) throws Exception {	
		this.currentPage = currentPage;
		if(currentPage < 1) {
			this.currentPage = 1;
		}
		if(currentPage > totalNumberOfPages) {
			this.currentPage = totalNumberOfPages;
		}
	}


	@Override
	public String toString() {
		return "PageBeanDto [dataList=" + dataList + ", countPerPage=" + countPerPage + ", totalNumberOfPages="
				+ totalNumberOfPages + ", currentPage=" + currentPage + "]";
	}
	
	
	
	
}
