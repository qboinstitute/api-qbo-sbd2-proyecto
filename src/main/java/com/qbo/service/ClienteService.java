package com.qbo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbo.model.Cliente;
import com.qbo.repository.ClienteRepository;

@Service
public class ClienteService implements BaseService<Cliente> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> obtenerTodo() {
		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> obtenerPorId(Long id) {
		Optional<Cliente> entityOptional =clienteRepository.findById(id);
		if(entityOptional.equals(null)) {
			return Optional.empty();
		}else {
			return entityOptional;
		}	
	}

	@Override
	public Cliente guardar(Cliente entity) {
		return clienteRepository.save(entity);
	}

	@Override
	public HashMap<String, String> eliminarPorId(Long id) {
		HashMap<String, String> respuesta = new HashMap<String, String>();
		clienteRepository.deleteById(id);
		respuesta.put("mensaje", "Elemento eliminado correctamente");
		return respuesta;
	}
	
	

	
}
