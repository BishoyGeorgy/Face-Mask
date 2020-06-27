package com.learning.kmama.hibernate.daosimp;

import com.learning.kmama.entities.Entity;
import com.learning.kmama.hibernate.util.TransactionUtil;
import org.apache.logging.log4j.util.TriConsumer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {

    Class entityClass;

    public AbstractDao(Class entityClass) {
        this.entityClass = entityClass;
    }

    private Query<T> createQuery(Session session, TriConsumer<CriteriaBuilder, Root<T>, CriteriaQuery<T>> caller){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        caller.accept(builder, root, query);
        return session.createQuery(query);
    }

    public T createQueryWithSingleResult(TriConsumer<CriteriaBuilder, Root<T>, CriteriaQuery<T>> caller) {
        return TransactionUtil.transactionReadOnly(session -> {
            Query<T> query = createQuery(session, caller);
            return query.getSingleResult();
        });
    }

    public List<T> createQueryWithListResult(TriConsumer<CriteriaBuilder, Root<T>, CriteriaQuery<T>> caller) {
        return TransactionUtil.transactionReadOnly(session -> {
            Query<T> query = createQuery(session, caller);
            return query.getResultList();
        });
    }

    public T getById(String id) {
        return createQueryWithSingleResult((builder, root, query) ->
                query.select(root).where(builder.equal(root.get("id"), id))
        );
        //return TransactionUtil.transactionReadOnly(session -> {
        //      Criteria criteria = session.createCriteria(entityClass).add(Restrictions.eq("ID", id));
        //      T entity = (T)criteria.uniqueResult();
        //      return entity;
        //});
    }

    public List<T> getAll() {
        return createQueryWithListResult((builder, root, query) -> query.select(root));
        //        return TransactionUtil.transactionReadOnly(session -> {
        //            Criteria criteria = session.createCriteria(entityClass);
        //            List<T> entities = criteria.list();
        //            return entities;
        //        });
    }

    public T insert(T entity){
        return TransactionUtil.transactionReadAndWrite(session -> {
            session.save(entity);
            List<T> entities = session.createCriteria(entityClass).list();
            return entities.get(entities.size()-1);
        });
    }

    public void delete(T entity){
        TransactionUtil.transactionWriteonly(session ->  {
            session.delete(entity);
        });
    }

    public void update(T entity){
        TransactionUtil.transactionWriteonly(session -> {
            session.saveOrUpdate(entity);
        });
    }


}
