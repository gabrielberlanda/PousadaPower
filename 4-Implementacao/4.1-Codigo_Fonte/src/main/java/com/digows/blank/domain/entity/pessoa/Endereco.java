/**
 * 
 */
package com.digows.blank.domain.entity.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Gabriel Berlanda
 *
 */
public class Endereco extends AbstractEntity implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2445612725677044611L;
	
	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/

	@Column( nullable = true, length= 50 )
	private String cep;
	
	@Column( nullable = true, length = 50 )
	private String bairro;
	
	@Column( nullable = true, length = 100 )
	private String complemento;
	
	@Column( nullable = true )
	private Integer numero;
	
	@ManyToOne( optional = true, fetch = FetchType.EAGER )
	private Cidade cidade;

	/*-------------------------------------------------------------------
	* 		 					CONSTRUTORES
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Endereco()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Endereco( Long id )
	{
		super( id );
	}
	
	/**
	 * @param cep
	 * @param bairro
	 * @param complemento
	 * @param numero
	 * @param cidade
	 */
	public Endereco( Long id, String cep, String bairro, String complemento, Integer numero, Cidade cidade )
	{
		super( id );
		this.cep = cep;
		this.bairro = bairro;
		this.complemento = complemento;
		this.numero = numero;
		this.cidade = cidade;
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
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
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
		Endereco other = ( Endereco ) obj;
		if ( bairro == null )
		{
			if ( other.bairro != null ) return false;
		}
		else if ( !bairro.equals( other.bairro ) ) return false;
		if ( cep == null )
		{
			if ( other.cep != null ) return false;
		}
		else if ( !cep.equals( other.cep ) ) return false;
		if ( cidade == null )
		{
			if ( other.cidade != null ) return false;
		}
		else if ( !cidade.equals( other.cidade ) ) return false;
		if ( complemento == null )
		{
			if ( other.complemento != null ) return false;
		}
		else if ( !complemento.equals( other.complemento ) ) return false;
		if ( numero == null )
		{
			if ( other.numero != null ) return false;
		}
		else if ( !numero.equals( other.numero ) ) return false;
		return true;
	}
	
	/*-------------------------------------------------------------------
	*						GETTERS AND SETTERS
	*-------------------------------------------------------------------*/
	
	/**
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep( String cep )
	{
		this.cep = cep;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro()
	{
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro( String bairro )
	{
		this.bairro = bairro;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento()
	{
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento( String complemento )
	{
		this.complemento = complemento;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero()
	{
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero( Integer numero )
	{
		this.numero = numero;
	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade()
	{
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade( Cidade cidade )
	{
		this.cidade = cidade;
	}

}
