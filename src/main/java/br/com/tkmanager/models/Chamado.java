package br.com.tkmanager.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="CHAMADO")
public class Chamado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="idchamado") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idchamado;
	
	
	@ManyToOne
	@JoinColumn(name="responsavel")
	private Tecnico responsavel;
	
	@ManyToOne
	@JoinColumn(name="relator")
	private Tecnico relator;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="status")
	private Character status;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="prioridade")
	private Character prioridade;
	
	@Column(name="impacto")
	private Character impacto;
	
	@Column(name="dtinclusao")
	private Date dtinclusao;

	
	public Tecnico getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Tecnico responsavel) {
		this.responsavel = responsavel;
	}

	public Tecnico getRelator() {
		return relator;
	}

	public void setRelator(Tecnico relator) {
		this.relator = relator;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Character prioridade) {
		this.prioridade = prioridade;
	}

	public Character getImpacto() {
		return impacto;
	}

	public void setImpacto(Character impacto) {
		this.impacto = impacto;
	}

	public Date getDtinclusao() {
		return dtinclusao;
	}

	public void setDtinclusao(Date dtinclusao) {
		this.dtinclusao = dtinclusao;
	}

	public Integer getIdchamado() {
		return idchamado;	
	}
	
	public void setIdchamado(Integer idchamado) {
		this.idchamado = idchamado;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dtinclusao == null) ? 0 : dtinclusao.hashCode());
		result = prime * result + ((idchamado == null) ? 0 : idchamado.hashCode());
		result = prime * result + ((impacto == null) ? 0 : impacto.hashCode());
		result = prime * result + ((prioridade == null) ? 0 : prioridade.hashCode());
		result = prime * result + ((relator == null) ? 0 : relator.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Chamado other = (Chamado) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dtinclusao == null) {
			if (other.dtinclusao != null)
				return false;
		} else if (!dtinclusao.equals(other.dtinclusao))
			return false;
		if (idchamado == null) {
			if (other.idchamado != null)
				return false;
		} else if (!idchamado.equals(other.idchamado))
			return false;
		if (impacto == null) {
			if (other.impacto != null)
				return false;
		} else if (!impacto.equals(other.impacto))
			return false;
		if (prioridade == null) {
			if (other.prioridade != null)
				return false;
		} else if (!prioridade.equals(other.prioridade))
			return false;
		if (relator == null) {
			if (other.relator != null)
				return false;
		} else if (!relator.equals(other.relator))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
}
