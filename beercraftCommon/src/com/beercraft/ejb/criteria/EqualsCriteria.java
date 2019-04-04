package com.beercraft.ejb.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * The two properties match exactly
 *
 */
public class EqualsCriteria extends TableCriteria {
	
	public EqualsCriteria(String propertyName, Object propertyValue) {
		super(propertyName, propertyValue);
	}

	@Override
	public <T> Predicate getPredicate(CriteriaBuilder builder, Root<T> root) {
		return builder.equal(root.get(propertyName),  searchValue);
	}
}
