package com.qbo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.convertdto.DtoUtil;
import com.qbo.dto.DtoEntity;
import com.qbo.dto.SalaDto;
import com.qbo.model.Cliente;
import com.qbo.model.Sala;
import com.qbo.service.SalaService;

@RestController
@RequestMapping(path = "api/v1/sala")
public class SalaController {

	@Autowired
	private SalaService salaService;
	
	
	
	@GetMapping("/full")
	public ResponseEntity<List<Sala>> obtenerTodo(){
		List<Sala> salas = new ArrayList<Sala>();
		salaService.obtenerTodo().forEach(salas::add);
		if(salas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(salas, HttpStatus.OK);		
	}
	
	@GetMapping("")
	public ResponseEntity<List<DtoEntity>> obtenerTodoDto(){
		List<DtoEntity> salas = new ArrayList<DtoEntity>();
		salas = salaService.obtenerTodo().stream()
				.map(x -> new DtoUtil().convertirADto(x, new SalaDto()))
				.collect(Collectors.toList());
		
		if(salas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(salas, HttpStatus.OK);		
	}
	
}
