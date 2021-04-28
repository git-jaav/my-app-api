package com.jaav.sys.myappapi.service;

import com.jaav.sys.myappapi.model.api.TipoCambioRequest;
import com.jaav.sys.myappapi.model.api.TipoCambioResponse;
import io.reactivex.Single;


public interface ProcessService {

    Single<TipoCambioResponse> executeProcess(TipoCambioRequest TipoCambioRq);
}
