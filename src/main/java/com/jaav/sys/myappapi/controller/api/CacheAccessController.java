package com.jaav.sys.myappapi.controller.api;

import com.jaav.sys.myappapi.model.api.DataCacheEntity;
import com.jaav.sys.myappapi.service.CacheAccessService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/api/channel/sys/v1/cache")
public class CacheAccessController {


    @Autowired
    private CacheAccessService cacheAccessService;


    @PostMapping(value = "/" ,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Guardar data en cache" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = String.class, httpMethod = "POST" ,
            notes = "Guardar data en cache", tags = "cache"
    )
    @ApiResponses({
            @ApiResponse( code = 200, message = "ok",response = String.class),
            @ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
    })
    public ResponseEntity<String> saveDataCache(@RequestBody() DataCacheEntity data) {
        data.setFormatedDateRegister(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        String keyResult = cacheAccessService.saveData(data);
        return new ResponseEntity<String>(keyResult, HttpStatus.OK);
    }

    @GetMapping(value = "/{datakey}" ,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Guardar data en cache" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = String.class, httpMethod = "GET" ,
            notes = "Ver un registro guardado en cache por si key", tags = "cache"
    )
    @ApiResponses({
            @ApiResponse( code = 200, message = "ok",response = String.class),
            @ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
    })
    public ResponseEntity<DataCacheEntity> getDataCache(
            @PathVariable(name = "datakey") String datakey) {
        return cacheAccessService.getData(datakey).map(data -> {
            return new ResponseEntity<DataCacheEntity>(data, HttpStatus.OK);
        }).orElse(new ResponseEntity<DataCacheEntity>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
