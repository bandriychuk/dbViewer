package com.database;

import java.lang.reflect.Method;

public interface Processor {

    String getName();

    Object process(Method method, Class<?> clazz, Object[] args);
}
