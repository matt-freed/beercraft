package com.beercom.ejb.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Represents a criteria to limit/filter the results of the JPA query
 *
 */
public abstract class TableCriteria {
	
	/**
	 * The field name on the entity that you want to search by
	 */
	protected String propertyName;
	
	/**
	 * The value of the field you specified above that you want to match
	 */
	protected Object searchValue;
	
	/**
	 * 
	 * @param propertyName
	 * @param searchValue
	 */
	public TableCriteria(String propertyName, Object searchValue) {
		this.propertyName = propertyName;
		this.searchValue = searchValue;
	}
	
	/**
	 * Each subclass must build a predicate to add to the query. This predicate
	 * determines how to compare the entity property and the specified value
	 * (equal, greater than, etc)
	 * @param builder
	 * @param root
	 * @return
	 */
	public abstract <T> Predicate getPredicate(CriteriaBuilder builder, Root<T> root);

}
