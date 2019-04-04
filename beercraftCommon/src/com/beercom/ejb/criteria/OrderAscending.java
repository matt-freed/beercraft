package com.beercom.ejb.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * Sorted in ascending order
 *
 */
public class OrderAscending extends Ordering{

	public OrderAscending(String propertyName) {
		super(propertyName);
	}
	
	@Override
	public <T> Order getOrderCriteria(CriteriaBuilder builder, Root<T> root) {
		return builder.asc(root.get(propertyName));
	}
}
