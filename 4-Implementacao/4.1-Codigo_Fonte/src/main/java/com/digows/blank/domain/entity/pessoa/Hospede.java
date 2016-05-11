/**
 * 
 */
package com.digows.blank.domain.entity.pessoa;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author BERLANDA
 *
 */
@Entity
@DiscriminatorValue( value = Pessoa.HOSPEDE_VALUE )

public class Hospede extends Pessoa implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7546938978181416165L;

	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	private String descritivoVeiculo;

	/**
	 * 
	 */
	private String observacao;

	/*-------------------------------------------------------------------
	* 		 					CONSTRUTORES
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Hospede()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Hospede( Long id )
	{
		super( id );
	}

	/*-------------------------------------------------------------------
	*							METODOS
	*-------------------------------------------------------------------*/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( descritivoVeiculo == null ) ? 0 : descritivoVeiculo.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
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
		Hospede other = ( Hospede ) obj;
		if ( descritivoVeiculo == null )
		{
			if ( other.descritivoVeiculo != null ) return false;
		}
		else if ( !descritivoVeiculo.equals( other.descritivoVeiculo ) ) return false;
		if ( observacao == null )
		{
			if ( other.observacao != null ) return false;
		}
		else if ( !observacao.equals( other.observacao ) ) return false;
		return true;
	}

	/*-------------------------------------------------------------------
	*						GETTERS AND SETTERS
	*-------------------------------------------------------------------*/
	
	/**
	 * @return the descritivoVeiculo
	 */
	public String getDescritivoVeiculo()
	{
		return descritivoVeiculo;
	}

	/**
	 * @param descritivoVeiculo the descritivoVeiculo to set
	 */
	public void setDescritivoVeiculo( String descritivoVeiculo )
	{
		this.descritivoVeiculo = descritivoVeiculo;
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
	
	
}
