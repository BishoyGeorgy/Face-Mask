package com.learning.kmama.hibernate.daosimp;

import com.learning.kmama.entities.Entity;
import com.learning.kmama.hibernate.util.TransactionUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public abstract class AbstractDao<T extends Entity> {

    Class entityClass;

    public AbstractDao(Class entityClass) {
        this.entityClass = entityClass;
    }

    public T getById(String id){
        return TransactionUtil.transactionReadOnly(session -> {
            Criteria criteria = session.createCriteria(entityClass).add(Restrictions.eq("ID", id));
            T entity = (T)criteria.uniqueResult();
            return entity;
        });
    }

    public T insert(T entity){
        return TransactionUtil.transactionReadAndWrite(session -> {
            session.save(entity);
            List<T> entities = session.createCriteria(entityClass).list();
            return entities.get(entities.size()-1);
        });
    }
}
