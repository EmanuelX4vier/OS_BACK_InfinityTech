package com.infinity.os.types;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {

    ANDAMENTO, CONCLUIDO, AGUARDANDO, AUTORIZADO, DISPONIVEL, INDISPONIVEL;

    @JsonCreator
    public static Status forValue(String value) {
        for (Status s : Status.values()) {
            if (s.name().equalsIgnoreCase(value)) {
                return s;
            }
        }
        return null; // Ou lance uma exceção customizada
    }
}
