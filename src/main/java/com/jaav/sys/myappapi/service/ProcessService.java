package com.jaav.sys.myappapi.service;

import com.jaav.sys.myappapi.model.api.TipoCambioRequest;
import com.jaav.sys.myappapi.model.api.TipoCambioResponse;
import rx.Single;


public interface ProcessService {

    Single<TipoCambioResponse> executeProcess(TipoCambioRequest TipoCambioRq);
}
