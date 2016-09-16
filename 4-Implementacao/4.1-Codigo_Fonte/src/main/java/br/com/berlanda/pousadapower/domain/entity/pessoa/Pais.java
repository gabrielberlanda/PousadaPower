/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Gabriel Berlanda
 *
 */
@Entity
@DataTransferObject(javascript = "Pais")
public class Pais extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2101744238861508309L;

	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Column( nullable = false, unique = true, length = 50 )
	private String nome;

	/**
	 * 
	 */
	private Boolean cpfRequerido;
	
	/*-------------------------------------------------------------------
	* 		 					CONSTRUTORES
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Pais()
	{
		super();
	}
	
	/**
	 * 
	 */
	public Pais( Long id )
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
		result = prime * result + ( cpfRequerido ? 1231 : 1237 );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
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
		Pais other = ( Pais ) obj;
		if ( cpfRequerido != other.cpfRequerido ) return false;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
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
	 * @return the cpfRequerido
	 */
	public boolean isCpfRequerido()
	{
		return cpfRequerido;
	}

	/**
	 * @param cpfRequerido the cpfRequerido to set
	 */
	public void setCpfRequerido( boolean cpfRequerido )
	{
		this.cpfRequerido = cpfRequerido;
	}

}
