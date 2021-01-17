package com.leliva.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.leliva.model.IEntity;
import com.szczytowski.genericdao.criteria.Criteria;
import com.szczytowski.genericdao.criteria.Criterion;
import com.szczytowski.genericdao.criteria.restriction.Conjunction;
import com.szczytowski.genericdao.criteria.restriction.Disjunction;
import com.szczytowski.genericdao.criteria.restriction.Junction;
import com.szczytowski.genericdao.criteria.restriction.Restrictions;

@Transactional
public class GenericDao<T extends IEntity<I>, I extends Serializable> extends AbstractGenericDao<T, I> {
	
    @SuppressWarnings("unchecked")
	public List<T> getAll() {
		return findByCriteria(Criteria.forClass(getGenericClass()));
	}
	
	protected final Object findUniqueByCriteria(Criteria criteria) throws NonUniqueResultException, NoResultException {
        return criteria.uniqueResult(getEntityManager());
    }
	
	@SuppressWarnings("rawtypes")
	protected List findByCriteria(Criteria criteria) {
        return criteria.list(getEntityManager());
    }
	
	protected Criteria createCriteria() {
		return createCriteria(getGenericClass());
	}
	
	protected Criteria createCriteria(Class<?> entityClass) {
		return Criteria.forClass(entityClass);
	}
	
	protected void addEqRestriction(Criteria criteria, String property, String value, boolean ignoreCase) {
		if ( ignoreCase ) {
			criteria.add(new IgnoredCaseSimpleExpression(property, value, "="));
		} else {
			criteria.add(Restrictions.eq(property, value));
		}
	}

	protected void addLikeRestriction(Junction junction, String property, String value, boolean upperCase) {
		
		addLikeRestriction(junction, property, value, upperCase, false);
	}
	
	protected void addLikeRestriction(Junction junction, String property, String value, boolean upperCase, boolean startsWith) {
		if (StringUtils.isBlank(value)) {
			return;
		} 

		value = value.trim();
			
		if (upperCase) {
			value = value.toUpperCase();
		}
		
		if (!startsWith) {
			value = "%" + value;
		}
		
		value = value + "%";

		junction.add(Restrictions.like(property, value));
	}
	
	protected void addILikeRestriction(Criteria criteria, String property, String value, boolean startsWith) {
		addLikeRestriction(criteria, property, value, startsWith, true);
	}
	
	protected void addLikeRestriction(Criteria criteria, String property, String value, boolean startsWith, boolean ignoreCase) {
		if (StringUtils.isBlank(value)) {
			return;
		} 

		value = value.trim();
			
		if (!startsWith) {
			value = "%" + value;
		}
		
		value = value + "%";

		if ( ignoreCase ) {
			criteria.add(Restrictions.ilike(property, value));
		} else {
			criteria.add(Restrictions.like(property, value));
		}
	}
	
	protected Disjunction disjunction(Criterion... criterions) {
		Disjunction disjunction = Restrictions.disjunction();
		for ( Criterion criterion : criterions ) {
			disjunction.add(criterion);
		}
		return disjunction;
	}
	
	protected Conjunction conjunction(Criterion... criterions) {
		Conjunction conjunction = Restrictions.conjunction();
		for ( Criterion criterion : criterions ) {
			conjunction.add(criterion);
		}
		return conjunction;
	}
}
