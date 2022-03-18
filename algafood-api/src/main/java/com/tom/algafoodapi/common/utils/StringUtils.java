package com.tom.algafoodapi.common.utils;

public class StringUtils {
    public static String entityNotExist(Long id, String entityName) {
        return String.format("%s com id: %d não existe", entityName,id);
    }

    public static String entityLinked(String entityName) {
        return String.format("Não é possivel exculuir a(o) %s, pois há vinculo no banco de dados ", entityName);
    }
}
