package com.jaav.sys.myappapi.service;

import com.jaav.sys.myappapi.model.api.ProcessEntity;

import java.io.IOException;
import java.util.List;

public interface ProcessService {

    List<ProcessEntity> executeProcess(String type) throws IOException;

    List<String> getStackProcess() throws IOException;

}
