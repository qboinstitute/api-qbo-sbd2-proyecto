package com.qbo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbo.repository.SalaRepository;
import com.qbo.model.Sala;

@Service
public class SalaService {

	@Autowired
	private SalaRepository salaRepository;
	
	
	public List<Sala> obtenerTodo(){
		return salaRepository.findAll();
	}
	
}
