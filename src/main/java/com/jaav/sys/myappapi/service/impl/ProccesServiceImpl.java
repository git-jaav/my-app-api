package com.jaav.sys.myappapi.service.impl;

import com.jaav.sys.myappapi.model.ProccesEntity;
import com.jaav.sys.myappapi.service.ProccesService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProccesServiceImpl implements ProccesService {

    @Override
    public List<ProccesEntity> executeProcces(String type) {

        String proceso = "PROC_X";
        return Arrays.asList(
                new ProccesEntity("PROC_X01","OK",10,0),
                new ProccesEntity("PROC_X02","OK",2,0),
                new ProccesEntity("PROC_X03","OK",4,6)
        );
    }
}
