package net.engining.gateway.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T> {
	T get(String arg0);

	T get(T arg0);

	T findUniqueByProperty(@Param("propertyName") String arg0, @Param("value") Object arg1);

	List<T> findList(T arg0);

	List<T> findAllList(T arg0);

	int insert(T arg0);

	int update(T arg0);

	int delete(T arg0);
}