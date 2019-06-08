package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageble;

public interface GenericJDBC<T> {
	List<T> query(String sql, Object... parameters);
	Long insert(Object object);
	void update(Object object);
	void delete(long id);
	<T> T findByID(long id);

	//List<T> search(Object object, Integer page , String sortBy, String sortType);
	List<T> search(Map<String, Object> property, Pageble pageble ,Object... where);
}
