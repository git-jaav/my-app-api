package com.jaav.sys.myappapi.service;

import com.jaav.sys.myappapi.model.ProccesEntity;

import java.util.List;

public interface ProccesService {

    List<ProccesEntity> executeProcces(String type);
}
