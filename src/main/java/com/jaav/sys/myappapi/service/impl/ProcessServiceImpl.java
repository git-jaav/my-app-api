package com.jaav.sys.myappapi.service.impl;

import com.jaav.sys.myappapi.config.ApplicationProperties;
import com.jaav.sys.myappapi.model.api.ProcessEntity;
import com.jaav.sys.myappapi.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    ApplicationProperties applicationProperties;


    @Override
    public List<ProcessEntity> executeProcess(String type) {
        LocalDateTime fechaActual = LocalDateTime.now();
        String proceso = applicationProperties.getProcessName();
        return Arrays.asList(
                ProcessEntity.builder()
                        .proccesName(proceso + "01").status("ok").countOk(10).currentTime(fechaActual)
                        .build(),
                ProcessEntity.builder()
                        .proccesName(proceso + "02").status("ok").countOk(2).currentTime(fechaActual).build(),
                ProcessEntity.builder()
                        .proccesName(proceso + "03").status("ok").countOk(4).currentTime(fechaActual).build()
        );
    }
}
