package com.jaav.sys.myappapi.repository;

import com.jaav.sys.myappapi.model.entities.TipoCambioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCambioRepository extends CrudRepository<TipoCambioEntity, Integer> {


    TipoCambioEntity findByTagDate(String tagDate);

    //@Query(value = "SELECT * FROM TIPO_CAMBIO WHERE TAG_DATE =?1 ", nativeQuery = true)
    //TipoCambioEntity findByTagDate(String tagDate);

}
