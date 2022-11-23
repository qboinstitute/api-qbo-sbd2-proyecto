package com.qbo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.exception.ResourceNotFoundException;
import com.qbo.model.Cliente;
import com.qbo.service.ClienteService;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(path = "api/v1/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("")
	public ResponseEntity<List<Cliente>> obtenerTodo(){
		List<Cliente> Clientes = new ArrayList<Cliente>();
		clienteService.obtenerTodo().forEach(Clientes::add);
		if(Clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(Clientes, HttpStatus.OK);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable("id") long id){
		Cliente Cliente = clienteService.obtenerPorId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found state by id = "
							+ id));
		return new ResponseEntity<>(Cliente, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente Cliente){
		Cliente newCliente = clienteService.guardar(Cliente);
		return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable("id") long id,
			@RequestBody Cliente Cliente){
		Cliente oldCliente = clienteService.obtenerPorId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found state by id = "
							+ id));
		oldCliente.setNomcliente(Cliente.getNomcliente());
		oldCliente.setApecliente(Cliente.getApecliente());
		oldCliente.setDnicliente(Cliente.getDnicliente());
		return new ResponseEntity<>(clienteService.guardar(oldCliente), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable("id") long id){
		clienteService.obtenerPorId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found state by id = "
							+ id));		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.eliminarPorId(id));
	}
	
	@GetMapping("/dni/{dni}")
	public ResponseEntity<Cliente> searchByDni(@PathVariable("dni")
			String dni){
		Cliente cliente = clienteService.searchByDni(dni)
				.orElseThrow(() -> new ResourceNotFoundException("Not found client by DNI = "
							+ dni));
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<?> searchByName(@RequestParam String nombre,
			Pageable pageable){
		Page<Cliente> clientes = clienteService.searchByNombre(nombre, pageable);
		if(clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

}
