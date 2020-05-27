package com.ragnax.ragnaxoauthusuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragnax.domain.oauthusuario.entidad.Usuario;
import com.ragnax.domain.response.error.RagnaxError;
import com.ragnax.ragnaxoauthusuario.exception.LogicaImplException;
import com.ragnax.ragnaxoauthusuario.servicio.OauthService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;

@RefreshScope
@RestController
//(value = { "${servicio.app.controller}" })
public class OauthUsuarioController {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(OauthUsuarioController.class);
	
	/****@GetMapping  no soporta Errors****/
	@Autowired
	OauthService oauthService;
	
	/*****
	##Swagger
	http://localhost:8084/swagger-ui.html#/
	*****/
	@Value("${configuracion.texto}")
	private String texto;
	/***************************************************/
	/*************** prueba *** *******************/
	/***************************************************/
	@ApiOperation(value = "prueba con Parametros -> http://localhost:8088/",  response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = String.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = String.class)
	})
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>  prueba()  throws LogicaImplException{
		return new ResponseEntity<>(texto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "prueba con Parametros -> http://localhost:8088/prueba/{idprueba}", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos de creacion", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = String.class)
	})
	@GetMapping(value = "${servicio.app.uri.pruebaParametros}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String>  pruebaConParametro( @ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") 
	@PathVariable String idprueba)  throws LogicaImplException{
		return new ResponseEntity<>(idprueba, HttpStatus.OK);
	}
	/***************************************************/
	/*************** Usuario ***************/
	/***************************************************/
	
	
	@ApiOperation(value = "Buscar Usuario Oauth", response = Usuario.class)
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Error al procesar los datos", response = RagnaxError.class),
			@ApiResponse(code = 503, message = "Error con el servicio", response = RagnaxError.class),
			@ApiResponse(code = 200, message = "Servicio ejecutado satisfactoriamente", response = Usuario.class)
	})
	@PostMapping(value = "${servicio.app.uri.buscarUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario>  buscarUsuario(@ApiParam(value = "objeto de entrada", required = true, defaultValue = "0") 
		@RequestBody @Valid  Usuario usuario, @ApiIgnore Errors errors)  throws LogicaImplException{

		return new ResponseEntity<>(oauthService.buscarUsuario(usuario), HttpStatus.OK);

	}
	
	
}
