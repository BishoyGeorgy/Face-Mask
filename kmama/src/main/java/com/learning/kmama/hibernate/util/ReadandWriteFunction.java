package com.learning.kmama.hibernate.util;

@FunctionalInterface
public interface ReadandWriteFunction <Session, T> {

    T apply(Session session);
}
