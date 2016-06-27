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
@Entity
@DataTransferObject(javascript = "Estado")
public class Estado extends AbstractEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3390619936599804735L;

	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Column( length = 50, nullable = false, unique = true )
	private String nome;
	
	/**
	 * 
	 */
	@ManyToOne( optional= false, fetch=FetchType.EAGER )
	private Pais pais;

	/*-------------------------------------------------------------------
	* 		 					CONSTRUTORES
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Estado()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Estado( Long id )
	{
		super( id );
	}
	
	/**
	 * @param name
	 * @param pais
	 */
	public Estado( Long id, String nome, Pais pais )
	{
		super( id );
		this.nome = nome;
		this.pais = pais;
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
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( pais == null ) ? 0 : pais.hashCode() );
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
		Estado other = ( Estado ) obj;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( pais == null )
		{
			if ( other.pais != null ) return false;
		}
		else if ( !pais.equals( other.pais ) ) return false;
		return true;
	}

	/*-------------------------------------------------------------------
	*						GETTERS AND SETTERS
	*-------------------------------------------------------------------*/
	
	/**
	 * @return the name
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param name the name to set
	 */
	public void setNome( String nome )
	{
		this.nome = nome;
	}

	/**
	 * @return the pais
	 */
	public Pais getPais()
	{
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais( Pais pais )
	{
		this.pais = pais;
	}
	
}
