package com.jaav.sys.myappapi.controller.api;

import com.jaav.sys.myappapi.utils.Constant;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/status")
public class StatusController {

	@GetMapping(value = "verify" ,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Verificar estado API" ,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ApiResponses({
			@ApiResponse( code = 200, message = "ok",response = String.class),
			@ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
	})
	public ResponseEntity<String> verificarStatus(){
		return new ResponseEntity<String>(Constant.RESULT_OK, HttpStatus.OK);
	}



	@GetMapping(value = "time" ,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "Verificar time" ,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	@ApiResponses({
			@ApiResponse( code = 200, message = "ok",response = String.class),
			@ApiResponse( code = 400, message = "BAD RQ",response = Exception.class)
	})
	public ResponseEntity<String> getTime(){
		Date fechaActual = new Date();
		String fecha = fechaActual.toString();
		if(fecha != null){
			return new ResponseEntity<String>("LOCAL"+"::"+fecha, HttpStatus.OK);
		}else{
			return new ResponseEntity<String>(Constant.RESULT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
