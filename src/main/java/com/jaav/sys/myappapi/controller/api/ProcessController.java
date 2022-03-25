package com.jaav.sys.myappapi.controller.api;

import com.jaav.sys.myappapi.model.api.ProcessEntity;
import com.jaav.sys.myappapi.service.ProcessService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JAAV
 */
@RestController
@RequestMapping("/api/sys/v1/process")
public class ProcessController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    ProcessService processService;

    @PostMapping(value = "/execute" ,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Ejecutar proceso X" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = ProcessEntity[].class, httpMethod = "POST" ,
            notes = "Endpoint de muestra para obtener una entidad de un proceso X", tags = "process"
    )
    @ApiResponses({
            @ApiResponse( code = 200, message = "ok",response = ProcessEntity[].class),
            @ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
    })
    public ResponseEntity<List<ProcessEntity>> execute(
            @ApiParam(value = "query param Tipo", type = "String", example = "ALL")
            @RequestParam(required = false, defaultValue = "") String type) {
        try {
            logger.info("[INFO - PROCESS]::start execution test. Type = "+type);
            return new ResponseEntity<List<ProcessEntity>>(
                    processService.executeProcess(type), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<List<ProcessEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /*@PostMapping(value = "/rx/execute" ,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Ejecutar proceso X" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
            //response = ProcessEntity[].class
    )
    @ApiResponses({
            @ApiResponse( code = 200, message = "ok",response = ProcessEntity[].class),
            @ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
    })
    public ResponseEntity<List<ProcessEntity>> rxExecute(
            @RequestParam(required = false, defaultValue = "") String type,
            ServerHttpRequest httpRequest) {
        try {
            return new ResponseEntity<List<ProcessEntity>>(
                    proccesService.executeProcces(type), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<List<ProcessEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
