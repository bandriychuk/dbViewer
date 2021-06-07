package com.database;

import org.jdbi.v3.core.Jdbi;

import java.lang.reflect.Method;
import java.util.Map;

public class JdbiQueryRunner extends QueryRunner {

    private Jdbi jdbi;

    public JdbiQueryRunner(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    protected Processor getProcessor(Method method) {
        String name = method.getName();
        Map<String, Processor> processors = getProcessors();
        Processor processor = processors.get(name);
        if (processor != null) {
            return processor;
        }

        throw new RuntimeException("No such processor for" + name);
    }
}
