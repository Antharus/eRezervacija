package com.leliva.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leliva.model.IEntity;

@Transactional
public abstract class AbstractGenericDao<T extends IEntity<I>, I extends Serializable> {

	private Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public AbstractGenericDao() {
		Type type = getClass().getGenericSuperclass();

		// check just in case if this class was proxied by cglib.
		if (!(type instanceof ParameterizedType)) {
			type = getClass().getSuperclass().getGenericSuperclass();
		}

		clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("eRezervacija");
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	public T getById(I id) {
		return entityManager.find(clazz, id);
	}

	public T load(I id) throws EntityNotFoundException {
		T entity = getById(id);
		if (entity == null) {
			throw new EntityNotFoundException("entity " + clazz + "#" + id + " was not found");
		}
		return entity;
	}

	public T save(T object) {
		entityManager.getTransaction().begin();
		if (object.getVersion() == null) {
			entityManager.persist(object);
		} else {
			object = entityManager.merge(object);
		}
		entityManager.getTransaction().commit();
		return object;
	}

	public T merge(T object) {
		return entityManager.merge(object);
	}

	public void delete(I id) {
		T entity = null;

		try {
			entity = getEntityManager().getReference(getGenericClass(), id);
		} catch (EntityNotFoundException e) {
			// Ignore it. Entity already deleted
		}
		if (entity != null) {
			delete(entity);
		}
	}

	public void delete(T object) {
		entityManager.remove(object);
	}

	public void refresh(T entity) {
		entityManager.refresh(entity);
	}

	public void flushAndClear() {
		entityManager.flush();
		entityManager.clear();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<T> saveAllInNewTransaction(List<T> entities) {
		return saveAll(entities);
	}

	public List<T> saveAll(List<T> entities) {
		List<T> saved = new ArrayList<T>(entities.size());
		for (T entity : entities) {
			saved.add(save(entity));
			// getEntityManager().flush();
		}

		return saved;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected Class<T> getGenericClass() {
		return clazz;
	}

}