/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.hospedagem;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject( javascript = "OrcamentoDiaria" )
public class OrcamentoDiaria extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -99094102331693263L;
	
	/**
	 * 
	 */
	@NotNull
	@Column( nullable = false )
	private Calendar dia;
	
	/**
	 * 
	 */
	@NotNull
	@Column( nullable = false )
	private Double valor;

	/**
	 * 
	 */
	public OrcamentoDiaria()
	{
		super();
	}

	/**
	 * @param id
	 */
	public OrcamentoDiaria( Long id )
	{
		super( id );
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( dia == null ) ? 0 : dia.hashCode() );
		result = prime * result + ( ( valor == null ) ? 0 : valor.hashCode() );
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
		OrcamentoDiaria other = ( OrcamentoDiaria ) obj;
		if ( dia == null )
		{
			if ( other.dia != null ) return false;
		}
		else if ( !dia.equals( other.dia ) ) return false;
		if ( valor == null )
		{
			if ( other.valor != null ) return false;
		}
		else if ( !valor.equals( other.valor ) ) return false;
		return true;
	}

	/**
	 * @return the dia
	 */
	public Calendar getDia()
	{
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia( Calendar dia )
	{
		this.dia = dia;
	}

	/**
	 * @return the valor
	 */
	public Double getValor()
	{
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor( Double valor )
	{
		this.valor = valor;
	}
	
}
