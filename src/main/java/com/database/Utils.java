package com.database;


public class Utils {
    public static String getTableName(Class<?> clazz) {
        String tableName = clazz.getSimpleName().toLowerCase();
        TableName annotation = clazz.getDeclaredAnnotation(TableName.class);
        if (annotation != null) {
            tableName = annotation.value();
        }

        return tableName;
    }
}
