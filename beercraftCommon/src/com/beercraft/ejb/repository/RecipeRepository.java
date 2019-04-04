package com.beercraft.ejb.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beercraft.ejb.criteria.OrderingBuilder;
import com.beercraft.ejb.criteria.TableCriteriaBuilder;
import com.beercraft.ejb.entity.BaseEntity;

/**
 * Repository representing a collection of recipe related information in the DB
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RecipeRepository{
	
	private static final Logger log = LogManager.getLogger(RecipeRepository.class);
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Get all entities of a given type
	 * @param entityClass
	 * @return
	 */
	public <T extends BaseEntity> List<T> getAllEntities(Class<T> entityClass){
		return getAllEntitiesForCriteria(entityClass, null, null);
	}
	
	/**
	 * Gets all entities for a given criteria
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> getAllEntitiesForCriteria(Class<T> entityClass, TableCriteriaBuilder criteria, OrderingBuilder order) {
		
		List<T> entities = null;
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);

		//from
		Root<T> root = query.from(entityClass);
		
		query.select(root);
		
		//where statements
		if(criteria != null) {
			query = query.where(criteria.buildPredicateList(criteriaBuilder, root));
		}
		
		//order by
		if(order != null) {
			query = query.orderBy(order.buildOrderList(criteriaBuilder, root));
		}
		
		try {
			
			entities = em.createQuery(query).getResultList();
		}
		catch (Exception e) {
			log.error("Error retrieving:" + entityClass, e);
			//TODO: throw exception
		}
		
		return entities;
		
	}
	
	/**
	 * Updates an existing entity in the database (should not be used for new entities)
	 * @param entity
	 */
	public <T> void update(T entity) {
		Set<T> entitySet = new HashSet<>();
		entitySet.add(entity);
		save(entitySet, false);
	}
	
	/**
	 * Insert a single entity to the database (should only be used for new entities)
	 * @param entity
	 */
	public <T> void insert(T entity) {
		Set<T> entitySet = new HashSet<>();
		entitySet.add(entity);
		insert(entitySet);
	}
	/**
	 * Insert multiple entities to the database (should only be used for new entities)
	 * @param entitySet
	 */
	public <T> void insert(Set<T> entitySet) {
		save(entitySet, true);
	}
	
	/**
	 * Persist a collection of entitites, and any cascading entities, to the DB>
	 * If it is a new entity, perform an insert (will assume it does not exist without checking).
	 * If the entity exists, perform a merge (will do a select first before updating for detached objects).
	 * @param entitySet
	 * @param newEntity
	 */
	private <T> void save(Set<T> entitySet, boolean newEntity) {
		
		try {
			for(T entity : entitySet) {
				if(newEntity) {
					em.persist(entity);
				}
				else {
					em.merge(entity);
				}
				
				//Forces write to occur here. If we need to write multiple entities at different 
				//times during the transacdtion we'll need to rethink this.
				em.flush();
			}
		}
		catch(ConstraintViolationException e) {
			log.info("Constraint violated", e);
			//TODO:  throw validation exception back up
			e.getConstraintViolations();
		}
		catch(Exception e) {
			log.error("Error saving", e);
			//TODO: throw exception
		}
	}
	
	/**
	 * Replace an existing entity in the database. Similar to an update, but performs a delete and then an 
	 * insert. THis can be useful for when you end to update a primary key value.
	 * @param oldEntity
	 * @param newEntity
	 */
	public <T extends BaseEntity> void replace (T oldEntity, T newEntity){
		
		try {
			T entityToBeRemoved = em.merge(oldEntity);
			em.remove(entityToBeRemoved);
			em.flush();
		}
		catch(Exception e) {
			log.error("Error replacing", e);
			//TODO: throw new exception
		}
		
		Set<T> entitySet = new HashSet<>();
		entitySet.add(newEntity);
		save(entitySet, true);
	}
	
	/**
	 * Deletes an entity from the DB
	 * @param entity
	 */
	public <T extends BaseEntity> void delete(T entity) {
		try {
			T entityToBeRemoved = em.merge(entity);
			em.remove(entityToBeRemoved);
		}
		catch(Exception e) {
			log.error("Error deleting", e);
			//TODO: exception
		}
	}
	
	/**
	 * Deletes all entities that match the given criteria
	 * @param entityClass
	 * @param criteria
	 */
	public <T extends BaseEntity> void deleteForCritieria(Class<T> entityClass, TableCriteriaBuilder criteria) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<T> deleteQuery = criteriaBuilder.createCriteriaDelete(entityClass);
		
		//from
		Root<T> root = deleteQuery.from(entityClass);
		
		//where statements
		if(criteria != null) {
			deleteQuery = deleteQuery.where(criteria.buildPredicateList(criteriaBuilder, root));
		}
		
		try {
			int rowsDeleted = em.createQuery(deleteQuery).executeUpdate();
			log.debug("Rows deleted successfully: " + rowsDeleted);
		}
		catch(Exception e) {
			log.error("Error bulk deleting",  e);
			//TODO: exception
		}
	}

}
