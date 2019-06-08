package com.laptrinhjavaweb.paging;

public interface Pageble {
	Integer getPage();
	Integer getObject();
	Integer getLimit();
	Sorter getSorter();
}
