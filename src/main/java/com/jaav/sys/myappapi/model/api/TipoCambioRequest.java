package com.jaav.sys.myappapi.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioRequest{

    private BigDecimal monto;
    private String tipoMonedaOrigen;
    private String tipoMonedaDestino;

}
