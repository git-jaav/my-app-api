package com.jaav.sys.myappapi.service.impl;

import com.jaav.sys.myappapi.model.api.TipoCambioRequest;
import com.jaav.sys.myappapi.model.api.TipoCambioResponse;
import com.jaav.sys.myappapi.service.ProcessService;
import com.jaav.sys.myappapi.utils.Constant;
import org.springframework.stereotype.Service;
import rx.Single;

import java.math.BigDecimal;

@Service
public class ProcessServiceImpl implements ProcessService {


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
        return Single.just(BigDecimal.valueOf(3.84));
    }

}
