package com.database;

import org.jdbi.v3.core.Jdbi;

import java.lang.reflect.Method;

public class FindOneByIdProcessor implements Processor {

    private final Jdbi jdbi;

    public FindOneByIdProcessor(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public String getName() {
        return "findOne";
    }

    @Override
    public Object process(Method method, Class<?> clazz, Object[] args) {
        String tableName = Utils.getTableName(clazz);
        String query = String.format("Select * FROM %s WHERE id = ?", tableName);
        return jdbi.withHandle(h -> h.select(query, args[0]).mapToBean(clazz).findOne().orElse(null));

    }
}
