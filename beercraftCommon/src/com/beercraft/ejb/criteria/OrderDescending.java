package com.beercraft.ejb.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * Sorted in descending order
 *
 */
public class OrderDescending extends Ordering{

	public OrderDescending(String propertyName) {
		super(propertyName);
	}
	
	@Override
	public <T> Order getOrderCriteria(CriteriaBuilder builder, Root<T> root) {
		return builder.desc(root.get(propertyName));
	}
}
