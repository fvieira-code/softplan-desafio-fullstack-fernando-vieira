package com.justica.processo.model.dto.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public interface RestDTO extends Serializable {
    @JsonIgnore
    String getCorrelationId();
    @JsonIgnore
    String getCodigoUsuario();
}
