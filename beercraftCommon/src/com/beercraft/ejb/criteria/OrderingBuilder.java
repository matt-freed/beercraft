package com.beercraft.ejb.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * A builder pattern for providing a convenient way to create
 * multiple ordering elements
 *
 */
public class OrderingBuilder {
	
	private List<Ordering> orderList = new ArrayList<>();

	private OrderingBuilder() {};
	
	/**
	 * Create a new instance
	 * @return
	 */
	public static OrderingBuilder newOrdering() {
		return new OrderingBuilder();
	}
	
	/**
	 * Adds the specified ordering to the list
	 * @param ordering
	 * @return
	 */
	public OrderingBuilder addOrdering (Ordering ordering) {
		orderList.add(ordering);
		return this;
	}
	
	/**
	 * Builds the list of ordering as required by JPA
	 * @param builder
	 * @param root
	 * @return
	 */
	public <T> List<Order> buildOrderList(CriteriaBuilder builder, Root<T> root){
		List<Order> orderCriteria = new ArrayList<>();
		
		if(orderList != null && orderList.isEmpty()) {
			for(Ordering order : orderList) {
				orderCriteria.add(order.getOrderCriteria(builder, root));
			}
		}
		
		return orderCriteria;
	}
}
