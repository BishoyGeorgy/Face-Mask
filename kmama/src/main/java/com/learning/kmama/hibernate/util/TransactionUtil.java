package com.learning.kmama.hibernate.util;

import org.hibernate.Session;

import java.util.function.Function;

public class TransactionUtil {

    public static <T> T transactionReadOnly(Function<Session, T> function){
        Session session = SessionManager.getSession();
        session.beginTransaction();
        T result = function.apply(session);
        return result;
    }

    public static <T> T transactionReadAndWrite(Function<Session, T> function){
        Session session = SessionManager.getSession();
        session.beginTransaction();
        T result = function.apply(session);
        session.getTransaction().commit();
        return result;
    }
}
