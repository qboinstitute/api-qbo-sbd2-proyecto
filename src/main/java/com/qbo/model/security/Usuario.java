package com.qbo.model.security;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {

	@Id
	private Long idusuario;
	
	private String nomusuario;
	
	private String cargousuario;
	
}
