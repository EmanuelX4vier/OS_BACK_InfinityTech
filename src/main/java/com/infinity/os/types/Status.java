package com.infinity.os.types;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {

    ANDAMENTO, CONCLUIDO, AGUARDANDO, AUTORIZADO, DISPONIVEL, INDISPONIVEL;

    @JsonCreator
    public static Status forValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Status não pode ser nulo.");
        }
        for (Status s : Status.values()) {
            if (s.name().equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException(
                "Status inválido: '" + value + "'. Valores aceitos: ANDAMENTO, CONCLUIDO, AGUARDANDO, AUTORIZADO, DISPONIVEL, INDISPONIVEL."
        );
    }
}
