package com.qbo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Optional<Cliente> searchByDni(String dniCliente){
		Optional<Cliente> entityOptional = clienteRepository.searchByDNIQueryNative(dniCliente);
		if(entityOptional.equals(null)) {
			return Optional.empty();
		}else {
			return entityOptional;
		}
	}
	
	public Page<Cliente> searchByNombre(String nombreCliente, 
			Pageable pageable){
		return clienteRepository.searchByNameQueryNative(nombreCliente, 
				pageable);
	}
	

	
}
