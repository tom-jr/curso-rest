package com.tom.algafoodapi.controllers.enums;

import lombok.Getter;

@Getter
public enum EnumErrorType {
    ENTITY_NOT_FOUND("/entidade-não-encontrada", "Entidade não encontrada"),
    ENTITY_LINKED("/entida-possui-viculos", "Entidade possui vínculos"),
    GENERAL_ERROR("/erro-de-negocio", "Error de Negocio"), 
    INVALIDE_FIELD("/campo-invalido","Campo Invalido"),
    INVALIDE_DATA("/dados-invalidos","Dados inválidos");

    private String title;

    private String uri;

    private EnumErrorType(String path, String title) {
        this.uri = "http://localhost/" + path;
        this.title = title;
    }

}
