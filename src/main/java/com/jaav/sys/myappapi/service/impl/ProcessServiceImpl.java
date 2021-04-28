package com.jaav.sys.myappapi.service.impl;

import com.jaav.sys.myappapi.model.api.TipoCambioRequest;
import com.jaav.sys.myappapi.model.api.TipoCambioResponse;
import com.jaav.sys.myappapi.model.entities.TipoCambioEntity;
import com.jaav.sys.myappapi.repository.TipoCambioRepository;
import com.jaav.sys.myappapi.service.ProcessService;
import com.jaav.sys.myappapi.utils.Constant;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ProcessServiceImpl implements ProcessService {


    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Override
    public Single<TipoCambioResponse>
        executeProcess(TipoCambioRequest TipoCambioRq) {
        return Single.zip(Single.just(TipoCambioRq) , getCurrentTipoCambio(),
                (rq, tipoCambio) -> {
                    if (Constant.TIPO_MONEDA_SOL.equalsIgnoreCase(rq.getTipoMonedaOrigen())) {
                        return new TipoCambioResponse(
                                rq.getMonto(), rq.getMonto().divide(tipoCambio, 4),
                                rq.getTipoMonedaOrigen(),
                                rq.getTipoMonedaDestino(),
                                tipoCambio);
                    } else if (Constant.TIPO_MONEDA_DOL.equalsIgnoreCase(rq.getTipoMonedaOrigen())) {
                        return new TipoCambioResponse(
                                rq.getMonto(), rq.getMonto().multiply(tipoCambio),
                                rq.getTipoMonedaOrigen(),
                                rq.getTipoMonedaDestino(),
                                tipoCambio
                        );
                    } else {
                        return new TipoCambioResponse(
                                rq.getMonto(), rq.getMonto(),
                                rq.getTipoMonedaOrigen(),
                                rq.getTipoMonedaDestino(),
                                tipoCambio
                                );
                    }
                });

    }


    /***
     *  tipo de cambio Actual**
     * @return
     */
    private Single<BigDecimal> getCurrentTipoCambio() {
        String tagDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        TipoCambioEntity result = tipoCambioRepository.findByTagDate(tagDate);
        //return Single.just(BigDecimal.valueOf(3.84));
        if (Optional.ofNullable(result).isPresent()) {
            return Single.just(result)
                    .flatMap(e -> Single.just(e.getExchangeRate()));
        } else {
            return Single.just(new TipoCambioEntity(1, tagDate,
                    BigDecimal.valueOf(4.00), "ENABLED"))
                    .flatMap(e -> Single.just(e.getExchangeRate()));
        }
    }

}
