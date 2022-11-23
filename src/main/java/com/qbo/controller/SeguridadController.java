package com.qbo.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qbo.exception.ResourceNotFoundException;
import com.qbo.model.security.Usuario;
import com.qbo.model.security.UsuarioResponse;
import com.qbo.service.SeguridadService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api/v1/seguridad")
public class SeguridadController {
	
	@Autowired
	private SeguridadService seguridadService;
	
	@PostMapping("/autenticacion")
	public ResponseEntity<UsuarioResponse> autenticarUsuario(
			@RequestParam("usuario") String usuario,
			@RequestParam("password") String password){
		Usuario objusuario = seguridadService
				.autenticarUsuario(usuario, password)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario o password incorrecto"));
		if(objusuario != null) {
			String token = generarToken(usuario, objusuario.getIdusuario());
			UsuarioResponse usuarioResponse = 
					new UsuarioResponse(objusuario.getIdusuario(), 
							objusuario.getNomusuario(), 
							token);
			return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
		}
		return null;
	}
	
	private String generarToken(String usuario, Long idusuario) {
		String clavesecreta = "${security.clave}";
		List<GrantedAuthority> listAutorizacion = 
				AuthorityUtils.createAuthorityList(
						seguridadService.listarRolesPorUsuario(idusuario));
		String token = Jwts
				.builder()
				.setId("@qboJWT")
				.setSubject(usuario)
				.claim("authorities", 
						listAutorizacion.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+30000000))
				.signWith(SignatureAlgorithm.HS512, clavesecreta.getBytes())
				.compact();
		return "Bearer "+token;
	}

}
