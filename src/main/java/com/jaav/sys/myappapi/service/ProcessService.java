package com.jaav.sys.myappapi.service;

import com.jaav.sys.myappapi.model.api.ProcessEntity;

import java.util.List;

public interface ProcessService {

    List<ProcessEntity> executeProcess(String type);
}
