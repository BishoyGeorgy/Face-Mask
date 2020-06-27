package com.learning.kmama.hibernate.util;

import org.hibernate.Session;

import java.util.function.Consumer;
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

    public static void transactionWriteonly(Consumer<Session> function){
        Session session = SessionManager.getSession();
        session.beginTransaction();
        function.accept(session);
        session.getTransaction().commit();
    }
}
