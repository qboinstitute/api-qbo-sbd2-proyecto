package com.qbo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.model.Estado;
import com.qbo.service.EstadoService;

@RestController
@RequestMapping(path ="api/v1/estado")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	
	@GetMapping("")
	public ResponseEntity<List<Estado>> obtenerTodo(){
		List<Estado> estados = new ArrayList<Estado>();
		estadoService.obtenerTodo().forEach(estados::add);
		if(estados.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(estados, HttpStatus.OK);		
	}
	
	
	
	

}
