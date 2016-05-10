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
@DataTransferObject(javascript = "Cidade")
public class Cidade extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031288450981144121L;

	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/
	@Column( length = 50, nullable = false, unique = true )
	private String name;
	
	@ManyToOne( optional = false, fetch = FetchType.EAGER )
	private Estado estado;

	/*-------------------------------------------------------------------
	* 		 					CONSTRUTORES
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Cidade()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Cidade( Long id )
	{
		super( id );
	}
	
	/**
	 * @param name
	 * @param estado
	 */
	public Cidade( Long id, String name, Estado estado )
	{
		super( id );
		this.name = name;
		this.estado = estado;
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
		result = prime * result + ( ( estado == null ) ? 0 : estado.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
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
		Cidade other = ( Cidade ) obj;
		if ( estado == null )
		{
			if ( other.estado != null ) return false;
		}
		else if ( !estado.equals( other.estado ) ) return false;
		if ( name == null )
		{
			if ( other.name != null ) return false;
		}
		else if ( !name.equals( other.name ) ) return false;
		return true;
	}

	/*-------------------------------------------------------------------
	*						GETTERS AND SETTERS
	*-------------------------------------------------------------------*/
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name )
	{
		this.name = name;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado()
	{
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado( Estado estado )
	{
		this.estado = estado;
	}
	
}
