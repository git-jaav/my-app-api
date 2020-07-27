package com.jaav.sys.myappapi.controller.api;


import com.jaav.sys.myappapi.model.entities.MeEncuestaTema;
import com.jaav.sys.myappapi.service.MeEncuestaTemaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JAAV
 */
@RestController
@RequestMapping("/api/channel/sys/v1/datamodel")
public class DataModelController {

    private static final Logger logger = LoggerFactory.getLogger(DataModelController.class);

    @Autowired
    MeEncuestaTemaService meEncuestaTemaService;

    @GetMapping(value = "/all" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "get all data demo" ,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET"
            //response = MeEncuestaTema[].class
    )
    @ApiResponses({
            @ApiResponse( code = 200, message = "ok",response = MeEncuestaTema[].class),
            @ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
    })
    public ResponseEntity<List<MeEncuestaTema>> findAll() {
        try {
            return new ResponseEntity<List<MeEncuestaTema>>(
                    meEncuestaTemaService.getAllElements().orElse(new ArrayList<>()),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity<List<MeEncuestaTema>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
