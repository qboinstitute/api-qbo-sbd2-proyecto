package com.qbo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.exception.ResourceNotFoundException;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> obtenerEstadoPorId(@PathVariable("id") long id){
		Estado estado = estadoService.obtenerPorId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found state by id = "
							+ id));
		return new ResponseEntity<>(estado, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Estado> crearEstado(@RequestBody Estado estado){
		Estado newestado = estadoService.guardar(new Estado(estado.getDescestado()));
		return new ResponseEntity<>(newestado, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> actualizarEstado(@PathVariable("id") long id,
			@RequestBody Estado estado){
		Estado oldestado = estadoService.obtenerPorId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found state by id = "
							+ id));
		oldestado.setDescestado(estado.getDescestado());
		return new ResponseEntity<>(estadoService.guardar(oldestado), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarEstado(@PathVariable("id") long id){
		estadoService.obtenerPorId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found state by id = "
							+ id));		
		return ResponseEntity.status(HttpStatus.OK).body(estadoService.eliminarPorId(id));
	}
	
	

}
