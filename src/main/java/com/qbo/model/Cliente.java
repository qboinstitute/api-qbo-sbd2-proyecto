package com.qbo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcliente;

	@Column(name = "nomcliente")
	private String nomcliente;
	@Column(name = "apecliente")
	private String apecliente;
	@Column(name = "dnicliente")
	private String dnicliente;
	
}
