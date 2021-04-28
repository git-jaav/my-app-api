package com.jaav.sys.myappapi.controller.api;

import com.jaav.sys.myappapi.model.api.TipoCambioRequest;
import com.jaav.sys.myappapi.model.api.TipoCambioResponse;
import com.jaav.sys.myappapi.service.ProcessService;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author JAAV
 */
@RestController
@RequestMapping("/api/jaav/tipocambio")
public class TipoCambioController {


    @Autowired
    ProcessService processService;

    @PostMapping(value = "/aplicar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<TipoCambioResponse>> processTipoCambio(
            @RequestBody TipoCambioRequest tipoCambioRq
            ) {
        return processService.executeProcess(tipoCambioRq)
                .map(e -> new ResponseEntity<TipoCambioResponse>( e, HttpStatus.OK));
    }

}
