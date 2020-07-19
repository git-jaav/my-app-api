package com.jaav.sys.myappapi.service.impl;

import com.jaav.sys.myappapi.model.api.ProcessEntity;
import com.jaav.sys.myappapi.service.ProcessService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Override
    public List<ProcessEntity> executeProcess(String type) {

        String proceso = "PROC_X";
        return Arrays.asList(
                new ProcessEntity("PROC_X01","OK",10,0),
                new ProcessEntity("PROC_X02","OK",2,0),
                new ProcessEntity("PROC_X03","OK",4,6)
        );
    }
}
