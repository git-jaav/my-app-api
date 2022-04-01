package com.jaav.sys.myappapi.service.impl;

import com.jaav.sys.myappapi.client.AnotherServiceClient;
import com.jaav.sys.myappapi.config.ApplicationProperties;
import com.jaav.sys.myappapi.model.api.ProcessEntity;
import com.jaav.sys.myappapi.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    AnotherServiceClient anotherServiceClient;

    @Override
    public List<ProcessEntity> executeProcess(String type) throws IOException {
        LocalDateTime fechaActual = LocalDateTime.now();
        String processName = applicationProperties.getProcessName();
        List<String> stackProcess = getStackProcess();

        return Arrays.asList(
                ProcessEntity.builder()
                        .proccesName(processName + "01").status("ok").countOk(10).currentTime(fechaActual)
                        .stackProcess(stackProcess)
                        .build(),
                ProcessEntity.builder()
                        .proccesName(processName + "02").status("ok").countOk(2).currentTime(fechaActual)
                        .stackProcess(stackProcess)
                        .build(),
                ProcessEntity.builder()
                        .proccesName(processName + "03").status("ok").countOk(4).currentTime(fechaActual)
                        .stackProcess(stackProcess)
                        .build()

        );
    }

    @Override
    public List<String> getStackProcess() throws IOException {
        String proceso = applicationProperties.getProcessName();
        if (applicationProperties.isStackAnotherService()) {
            Response<List<String>> response =
                    anotherServiceClient.getProcessStack().execute();
            if (response.isSuccessful()) {
                response.body().add(proceso);
                return response.body();
            }else {
                throw new IOException(response.errorBody() != null
                        ? response.errorBody().string() : "Unknown error");
            }
        } else {
            return Arrays.asList(proceso);
        }
    }
}
