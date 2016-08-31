/**
 * 
 */
package com.digows.blank.domain.entity.tipoquarto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import com.digows.blank.domain.entity.tipoquarto.tarifa.Tarifa;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject ( javascript = "TipoQuarto" )
public class TipoQuarto extends AbstractEntity implements Serializable

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 713744878764666057L;

	/**
	 * 
	 */
	@NotNull ( message= "Nome é obrigatório")
	@Column( nullable = false, unique = true, length = 50)
	private String nome;
	
	/**
	 * 
	 */
	@NotNull( message= "Ocupação máxima é obrigatório" )
	@Column( nullable = false )
	private Integer ocupacaoMaxima;
	
	/**
	 * 
	 */
	@Column( length = 500 )
	private String observacao;
	
	/**
	 * 
	 */
	@OneToMany( fetch = FetchType.EAGER, cascade= CascadeType.ALL )
	private List<Tarifa> tarifasPadrao;

	/**
	 * 
	 */
	public TipoQuarto()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 */
	public TipoQuarto( Long id )
	{
		super( id );
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( ocupacaoMaxima == null ) ? 0 : ocupacaoMaxima.hashCode() );
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( !super.equals( obj ) ) return false;
		if ( getClass() != obj.getClass() ) return false;
		TipoQuarto other = ( TipoQuarto ) obj;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( observacao == null )
		{
			if ( other.observacao != null ) return false;
		}
		else if ( !observacao.equals( other.observacao ) ) return false;
		if ( ocupacaoMaxima == null )
		{
			if ( other.ocupacaoMaxima != null ) return false;
		}
		else if ( !ocupacaoMaxima.equals( other.ocupacaoMaxima ) ) return false;
		return true;
	}
	


	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome( String nome )
	{
		this.nome = nome;
	}

	/**
	 * @return the ocupacaoMaxima
	 */
	public Integer getOcupacaoMaxima()
	{
		return ocupacaoMaxima;
	}

	/**
	 * @param ocupacaoMaxima the ocupacaoMaxima to set
	 */
	public void setOcupacaoMaxima( Integer ocupacaoMaxima )
	{
		this.ocupacaoMaxima = ocupacaoMaxima;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao()
	{
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao( String observacao )
	{
		this.observacao = observacao;
	}

	/**
	 * @return the tarifasPadrao
	 */
	public List<Tarifa> getTarifasPadrao()
	{
		return tarifasPadrao;
	}

	/**
	 * @param tarifasPadrao the tarifasPadrao to set
	 */
	public void setTarifasPadrao( List<Tarifa> tarifasPadrao )
	{
		this.tarifasPadrao = tarifasPadrao;
	}
	
	
	
}
