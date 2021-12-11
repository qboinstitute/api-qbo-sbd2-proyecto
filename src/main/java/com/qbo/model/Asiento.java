package com.qbo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asiento")
public class Asiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idasiento;
	
	@Column(name = "nroasiento")
	private Integer nroasiento;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idsala")
	private Sala sala;

	public Asiento(Long idasiento, Integer nroasiento) {
		super();
		this.idasiento = idasiento;
		this.nroasiento = nroasiento;
	}
	
	
}
