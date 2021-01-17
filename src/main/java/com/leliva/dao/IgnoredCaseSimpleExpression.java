package com.leliva.dao;

import com.szczytowski.genericdao.criteria.Criteria;
import com.szczytowski.genericdao.criteria.Criteria.CriteriaQuery;
import com.szczytowski.genericdao.criteria.Criterion;

public class IgnoredCaseSimpleExpression implements Criterion {

    private final String property;
    private final String value;
    private final String operator;

    public IgnoredCaseSimpleExpression(String property, String value) {
        this(property, value, "=");
    }
    
    public IgnoredCaseSimpleExpression(String property, String value, String operator) {
        this.property = property;
        this.value = value;
        this.operator = operator;
    }

	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		criteriaQuery.setParam(value.toUpperCase());
        return "UPPER("+criteriaQuery.getPropertyName(property, criteria) + ")" + operator + "?1";
	}

}