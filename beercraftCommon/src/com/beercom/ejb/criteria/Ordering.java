package com.beercom.ejb.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * Represents an ordering that you want the results of the JPA query to be in 
 *
 */
public abstract class Ordering {

	/**
	 * The field name on the entity you want to order by
	 */
	protected String propertyName;
	
	/**
	 * Gets the order object to be added to the query. The subclass determines
	 * which order to be applied (ascending, descending, etc)
	 */
	
	public abstract <T> Order getOrderCriteria(CriteriaBuilder builder, Root<T> root);
	
	protected Ordering(String propertyName) {
		this.propertyName = propertyName;
	}
}
