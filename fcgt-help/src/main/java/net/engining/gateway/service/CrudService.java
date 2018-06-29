package net.engining.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import net.engining.gateway.dao.BaseDao;
import net.engining.gateway.entity.BaseEntity;

@Transactional(readOnly = true)
public abstract class CrudService<D extends BaseDao<T>, T extends BaseEntity> {
	@Autowired
	protected D dao;

	public T get(String id) {
		return (T) this.dao.get(id);
	}

	public T get(T entity) {
		return (T) this.dao.get(entity);
	}

	public List<T> findList(T entity) {
		return this.dao.findList(entity);
	}

	@Transactional(readOnly = false)
	public void insert(T entity) {
		this.dao.insert(entity);

	}

	@Transactional(readOnly = false)
	public void update(T entity) {
		this.dao.update(entity);
	}

	@Transactional(readOnly = false)
	public void delete(T entity) {
		this.dao.delete(entity);
	}

	public D getDao() {
		return this.dao;
	}
}