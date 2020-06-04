package com.cliente.cliente.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cliente.cliente.model.enuns.TipoTelefoneEnum;

@Entity
@Table(name="telefones", schema="cliente")
public class Telefone {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="tipo", length=15, nullable=false)
	@Enumerated
	private TipoTelefoneEnum tipoTelefone;
	
	@Column(name="numero", nullable=false)
	private long numero;
	
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable=false)
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTelefoneEnum getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefoneEnum tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (numero ^ (numero >>> 32));
		result = prime * result + ((tipoTelefone == null) ? 0 : tipoTelefone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (id != other.id)
			return false;
		if (numero != other.numero)
			return false;
		if (tipoTelefone != other.tipoTelefone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", tipoTelefone=" + tipoTelefone + ", numero=" + numero + ", cliente=" + cliente
				+ "]";
	}

}
