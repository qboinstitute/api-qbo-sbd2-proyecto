package com.qbo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbo.model.Estado;
import com.qbo.repository.EstadoRepository;

@Service
public class EstadoService implements BaseService<Estado> {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public List<Estado> obtenerTodo() {
		return estadoRepository.findAll();
	}

	@Override
	public Optional<Estado> obtenerPorId(Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		if(estado.equals(null)) {
			return estado.empty();
		}else {
			return estado;
		}
	}

	@Override
	public Estado guardar(Estado entity) {
		return estadoRepository.save(entity);
	}

	@Override
	public HashMap<String, String> eliminarPorId(Long id) {
		HashMap<String, String> respuesta = new HashMap<String, String>();
		estadoRepository.deleteById(id);
		respuesta.put("mensaje", "Elemento eliminado correctamente");
		return respuesta;
	}
	
	

	
	
}
