package com.laptrinhjavaweb.paging;

public class PageRequest implements Pageble{

	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;
	
	public PageRequest(int page, int maxPageItem,Sorter sorter) {
		// TODO Auto-generated constructor stub
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}
	
	@Override
	public Integer getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	@Override
	public Integer getObject() {
		// TODO Auto-generated method stub
		if(page != null && maxPageItem !=null) {
			return (page-1) * maxPageItem;
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		// TODO Auto-generated method stub
		return maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		// TODO Auto-generated method stub
		return sorter;
	}
	
	
	
}
