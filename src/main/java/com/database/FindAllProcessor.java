package com.database;

import org.jdbi.v3.core.Jdbi;

import java.lang.reflect.Method;

public class FindAllProcessor implements Processor {

    private Jdbi jdbi;

    public FindAllProcessor(Jdbi jdbi) {

        this.jdbi = jdbi;
    }

    @Override
    public String getName() {
        return "findAll";
    }

    @Override
    public Object process(Method method, Class<?> clazz, Object[] args) {
        String tableName = Utils.getTableName(clazz);
        String query = String.format("Select * FROM %s", tableName);
        return jdbi.withHandle(h -> h.select(query).mapToBean(clazz).list());
    }
}
