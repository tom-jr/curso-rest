package com.tom.algafoodapi.common.utils;

public class StringUtils {
    public static Object entityNotExist(Long id, String entityName) {
        return String.format("%s com id: %d não existe", entityName,id);
    }

    public static String entityViculate(String entityName) {
        return String.format("Não é possivel exculuir a(o) %s, pois há vinculo no banco de dados ", entityName);
    }
}
