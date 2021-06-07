package com.database;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class QueryRunner {

    private final Map<String, Processor> processors = new HashMap<>();

    protected final void addProcessor(Processor processor) {
        processors.put(processor.getName(), processor);
    }

    public Map<String, Processor> getProcessors() {
        return processors;
    }

    //    @Override
//    public final Map<String, Processor> getProcessors(){
//        return processors;
//    }

    protected abstract Processor getProcessor(Method method);

    protected Object process(Method method, Class<?> clazz, Object[] args) {
        Processor processor = getProcessor(method);
        return processor.process(method, clazz, args);
    }

}
