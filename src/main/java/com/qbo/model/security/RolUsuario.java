package com.qbo.model.security;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RolUsuario {

	@Id
	private Long idrol;
	
	private String nomrol;
	
	
	
}
