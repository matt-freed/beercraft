package com.beercom.ejb.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * A builder pattern for providing a convenient way to create
 * multiple criteria elements
 *
 */
public class TableCriteriaBuilder {

	private List<TableCriteria> criteriaList = new ArrayList<>();
	
	private TableCriteriaBuilder() {};
	
	/**
	 * Creates a new instance of a builder
	 * @return
	 */
	public static TableCriteriaBuilder newTableCriteria() {
		return new TableCriteriaBuilder();
	}
	
	public TableCriteriaBuilder add(TableCriteria criteria) {
		criteriaList.add(criteria);
		return this;
	}
	
	/**
	 * Builds the array of predicates as required by JPA
	 * @param builder
	 * @param root
	 * @return
	 */
	public <T> Predicate[] buildPredicateList (CriteriaBuilder builder, Root<T> root) {
		Predicate[] predicateArray = null;
		
		if(criteriaList != null && !criteriaList.isEmpty()) {
			List<Predicate> conditionList = new ArrayList<>();
			
			for(TableCriteria criteria : criteriaList) {
				Predicate condition = criteria.getPredicate(builder, root);
				conditionList.add(condition);
			}
			
			predicateArray = conditionList.toArray(new Predicate[conditionList.size()]);
		}
		
		return predicateArray;
	}
	
}
