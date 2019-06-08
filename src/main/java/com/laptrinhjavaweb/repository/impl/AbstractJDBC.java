package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.repository.GenericJDBC;

public class AbstractJDBC<T> implements GenericJDBC<T> {

	private Class<T> zClass;

	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String databaseURL = "jdbc:mysql://localhost:3306/estate_javaweb_learning";
			String userName = "root";
			String password = "123456";
			return DriverManager.getConnection(databaseURL, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> query(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();

		try (Connection conn = getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultset = statement.executeQuery();) {
			if (conn != null) {
				return resultSetMapper.mapRow(resultset, this.zClass);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Long insert(Object object) {
		// get
		Long id = null;

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLInsert();
			// zClass: BuildingEntity
			// tableName: building

			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (conn != null) {
				Class<?> zClass = object.getClass();
				// zClass khác zClass bên trên là nó có values, chứa data trong đó
				// zClass bên trên chỉ là rỗng thôi
				Field[] fields = zClass.getDeclaredFields();
				// set parameters
				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i]; // lấy field từ class hiện tại (NAME, STREET, WARD....)
					field.setAccessible(true); // object cũng giống như một cô gái, muộn đụng vô thì phải xin cấp quyền
					statement.setObject(index, field.get(object));
				}

				// check parent class
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				while (parentClass != null) {

					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i]; // lấy field từ class parent (createBy,
																			// CreateDate)
						field.setAccessible(true); // object cũng giống như một cô gái, muộn đụng vô thì phải xin cấp
													// quyền
						statement.setObject(indexParent, field.get(object));
						indexParent = indexParent + 1;
					}
					parentClass = parentClass.getSuperclass();
				}

				int rowsInserted = statement.executeUpdate();
				resultset = statement.getGeneratedKeys();
				conn.commit();
				if (rowsInserted > 0) {
					while (resultset.next()) {
						id = resultset.getLong(1);
						return id;
					}
				}
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		// get

		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLUpdate();
			// zClass: BuildingEntity
			// tableName: building

			statement = conn.prepareStatement(sql);
			if (conn != null) {
				Class<?> zClass = object.getClass();
				// zClass khác zClass bên trên là nó có values, chứa data trong đó
				// zClass bên trên chỉ là rỗng thôi
				Field[] fields = zClass.getDeclaredFields();
				// set parameters
				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i]; // lấy field từ class hiện tại (NAME, STREET, WARD....)
					field.setAccessible(true); // object cũng giống như một cô gái, muộn đụng vô thì phải xin cấp quyền
					statement.setObject(index, field.get(object));
				}

				// check parent class
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				Object id = null;
				while (parentClass != null) {

					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i]; // lấy field từ class parent (createBy,
																			// CreateDate)
						field.setAccessible(true); // object cũng giống như một cô gái, muộn đụng vô thì phải xin cấp
													// quyền

						String name = field.getName();
						if (!name.equals("id")) {
							statement.setObject(indexParent, field.get(object));
							indexParent = indexParent + 1;
						} else {
							id = field.get(object);
						}
					}
					parentClass = parentClass.getSuperclass();
				}
				statement.setObject(indexParent, id);
				statement.executeUpdate();
				conn.commit();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			String tableName = "";
			if (zClass.isAnnotationPresent(Table.class)) {
				Table table = zClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			
			String sql = "DELETE FROM "+tableName+" WHERE id = ?";

			statement = conn.prepareStatement(sql);
			if (conn != null) {
				statement.setObject(1, id);
				statement.executeUpdate();
				conn.commit();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	@Override
	public List<T> search(Map<String, Object> property, Pageble pageble, Object... where) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement statement = null;
		ResultSet resultset = null;

		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		
		StringBuilder sql = createSQLSearch(property);
		if(where != null && where.length>0) {
			sql.append(where[0]);
		}
		
		if(pageble != null) {
			if(pageble.getSorter()!=null) {
				Sorter sorter = pageble.getSorter();
				sql.append(" ORDER BY "+sorter.getSortName()+" "+sorter.getSortBy()+"");
			}
			if(pageble.getObject()!=null && pageble.getLimit()!=null) {
				sql.append(" LIMIT "+pageble.getObject()+", "+pageble.getLimit()+"");
			}		
		}

		try {
			conn = getConnection();

			statement = conn.createStatement();
			resultset = statement.executeQuery(sql.toString());
			if (conn != null) {
				return resultSetMapper.mapRow(resultset, this.zClass);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
				statement.close();
				resultset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/*
	@Override
	public List<T> search(Object object, Integer page, String sortBy, String sortType) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;

		List<T> id = new ArrayList<>();
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql = createSQLSearch(page, sortBy, sortType);
			statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (conn != null) {
				Class<?> zClass = object.getClass();
				Field[] fields = zClass.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					int index = i + 1;
					Field field = fields[i]; // lấy field từ class hiện tại (NAME, STREET, WARD....)
					field.setAccessible(true); // object cũng giống như một cô gái, muộn đụng vô thì phải xin cấp quyền

					if (field.get(object) != null) {
						statement.setObject(index, "%" + field.get(object) + "%");
					} else {
						statement.setObject(index, "%%");
					}

				}

				// check parent class
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length + 1;
				while (parentClass != null) {

					for (int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i]; // lấy field từ class parent (createBy,
																			// CreateDate)
						field.setAccessible(true); // object cũng giống như một cô gái, muộn đụng vô thì phải xin cấp
													// quyền

						if (field.get(object) != null) {
							statement.setObject(indexParent, "%" + field.get(object) + "%");
						} else {
							statement.setObject(indexParent, "%%");
						}

						indexParent = indexParent + 1;
					}
					parentClass = parentClass.getSuperclass();
				}
				resultset = statement.executeQuery();
				System.out.println(statement);
				conn.commit();
				return resultSetMapper.mapRow(resultset, this.zClass);
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	*/
	

	@Override
	public <T> T findByID(long id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;

		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		String sql = "SELECT * FROM "+tableName+" WHERE id = ?";

		try {
			conn = getConnection();

			statement = conn.prepareStatement(sql);
			statement.setObject(1, id);
			resultset = statement.executeQuery();
			if (conn != null) {
				return resultSetMapper.mapRow(resultset, this.zClass).get(0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
				statement.close();
				resultset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private String createSQLInsert() {
		// ĐÂY LÀ PHƯƠNG THỨC TẠO CÂU SQL
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}

		StringBuilder fields = new StringBuilder(""); // Dựa vào mấy cái name trong @column để get name
		StringBuilder params = new StringBuilder("");

		// VIẾT QUERY CHO CLASS CON - (name, street....) values (?,?,...)
		for (Field field : zClass.getDeclaredFields()) { // get mảng các column trong entity
			if (fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}

		}

		// VIẾT QUERY CHO CLASS CHA - (createBy, createDate....) values (?,?,...)
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) { // get mảng các column trong entity thằng cha
				if (fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					params.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		// QUERY INSERT
		String sql = "INSERT INTO " + tableName + "(" + fields.toString() + ") VALUES(" + params.toString() + ")";
		System.out.println(sql);
		return sql;
	}

	private String createSQLUpdate() {
		// ĐÂY LÀ PHƯƠNG THỨC TẠO CÂU SQL
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}

		// KHÚC KHÁC VỚI INSERT
		StringBuilder sets = new StringBuilder();
		String where = null;

		// VIẾT QUERY CHO CLASS CON - (name, street....) values (?,?,...)
		for (Field field : zClass.getDeclaredFields()) { // get mảng các column trong entity
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				String value = columnName + " = ?";

				if (!columnName.equals("id")) {
					if (sets.length() > 1) {
						sets.append(", ");
					}
					sets.append(value);
				}
			}

		}

		// VIẾT QUERY CHO CLASS CHA - (createBy, createDate....) values (?,?,...)
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) { // get mảng các column trong entity thằng cha
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name();
					String value = columnName + " = ?";

					if (!columnName.equals("id")) {
						if (sets.length() > 1) {
							sets.append(", ");
						}
						sets.append(value);
					} else {
						where = " WHERE " + value;
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		// QUERY UPDATE
		String sql = "UPDATE " + tableName + " SET " + sets.toString() + where;
		System.out.println(sql);
		return sql;
	}
	
	private StringBuilder createSQLSearch(Map<String, Object> property) {
		// TODO Auto-generated method stub
		
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		StringBuilder result = new StringBuilder("SELECT * FROM "+tableName+ " WHERE 1=1");
		if(property != null && property.size() > 0) {
			String[] params = new String[property.size()];
			Object[] values = new Object[property.size()];
			int i = 0;
			for (Map.Entry<?,?> item: property.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for (int j = 0; j < values.length; j++) {
				if(values[j] instanceof String) {
					result.append(" AND LOWER("+params[j]+") LIKE '%"+values[j]+"%'");
				}else if(values[j] instanceof Integer) {
					result.append(" AND "+params[j]+"="+values[j]+"");
				}
				
			}
		}
		return result;
	}

	/*
	private String createSQLSearch(Integer page, String sortBy, String sortType) {
		// ĐÂY LÀ PHƯƠNG THỨC TẠO CÂU SQL
		String tableName = "";
		if (zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}

		StringBuilder sets = new StringBuilder();

		// VIẾT QUERY CHO CLASS CON - (name, street....) values (?,?,...)
		for (Field field : zClass.getDeclaredFields()) { // get mảng các column trong entity
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				String value = "(" + columnName + " like ? or " + columnName + " is null" + ")";
				if (sets.length() > 1) {
					sets.append(" AND ");
				}
				sets.append(value);
			}

		}

		// VIẾT QUERY CHO CLASS CHA - (createBy, createDate....) values (?,?,...)
		// check parent class
		Class<?> parentClass = zClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) { // get mảng các column trong entity thằng cha
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name();
					String value = "(" + columnName + " like ? or " + columnName + " is null" + ")";
					if (sets.length() > 1) {
						sets.append(" AND ");
					}
					sets.append(value);
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		// QUERY SEARCH
		// SELECT *FROM building WHERE id = ;
		String sql = "SELECT * FROM " + tableName + " WHERE " + sets.toString() + "ORDER BY " + sortBy + " " + sortType
				+ " LIMIT " + page;
		System.out.println(sql);
		return sql;
	}
	*/
}
