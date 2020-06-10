package com.jaav.sys.myappapi.controller.api;

import com.jaav.sys.myappapi.model.ProccesEntity;
import com.jaav.sys.myappapi.service.ProccesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
///import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JAAV
 */
@RestController
@RequestMapping("/api/channel/sys/v1/procces")
public class ProccesController {

    private static final Logger logger = LoggerFactory.getLogger(ProccesController.class);

    @Autowired
    ProccesService proccesService;

    @PostMapping(value = "/execute" ,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Ejecutar proceso X" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
            //response = ProccesEntity[].class
    )
    @ApiResponses({
            @ApiResponse( code = 200, message = "ok",response = ProccesEntity[].class),
            @ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
    })
    public ResponseEntity<List<ProccesEntity>> execute(
            @RequestParam(required = false, defaultValue = "") String type) {
        try {
            return new ResponseEntity<List<ProccesEntity>>(
                    proccesService.executeProcces(type), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<List<ProccesEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /*@PostMapping(value = "/rx/execute" ,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Ejecutar proceso X" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
            //response = ProccesEntity[].class
    )
    @ApiResponses({
            @ApiResponse( code = 200, message = "ok",response = ProccesEntity[].class),
            @ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
    })
    public ResponseEntity<List<ProccesEntity>> rxExecute(
            @RequestParam(required = false, defaultValue = "") String type,
            ServerHttpRequest httpRequest) {
        try {
            return new ResponseEntity<List<ProccesEntity>>(
                    proccesService.executeProcces(type), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<List<ProccesEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
