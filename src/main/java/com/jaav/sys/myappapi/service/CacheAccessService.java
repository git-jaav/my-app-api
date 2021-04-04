package com.jaav.sys.myappapi.service;

import com.jaav.sys.myappapi.model.api.DataCacheEntity;

import java.util.Optional;

public interface CacheAccessService {

    String saveData(DataCacheEntity data);
    Optional<DataCacheEntity> getData(String key);
}
