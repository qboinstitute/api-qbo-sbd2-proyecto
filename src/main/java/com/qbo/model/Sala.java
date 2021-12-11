package com.qbo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sala")
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idsala;
	@Column(name = "descsala")
	private String descsala;
	@Column(name = "asientos")
	private Integer asientos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idestado")
	private Estado estado;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Asiento> listasiento = new ArrayList<Asiento>();
	
	
	
}
