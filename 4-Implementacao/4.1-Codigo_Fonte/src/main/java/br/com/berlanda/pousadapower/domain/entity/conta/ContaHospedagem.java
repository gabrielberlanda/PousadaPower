/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.conta;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.berlanda.pousadapower.domain.entity.hospedagem.Hospedagem;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject( javascript = "ContaHospedagem" )
public class ContaHospedagem extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2518514557023857190L;
	
	@OneToOne( fetch=FetchType.EAGER )
	private Hospedagem hospedagem;

	/**
	 * 
	 */
	public ContaHospedagem()
	{
		super();
	}

	/**
	 * @param id
	 */
	public ContaHospedagem( Long id )
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
		result = prime * result + ( ( hospedagem == null ) ? 0 : hospedagem.hashCode() );
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
		ContaHospedagem other = ( ContaHospedagem ) obj;
		if ( hospedagem == null )
		{
			if ( other.hospedagem != null ) return false;
		}
		else if ( !hospedagem.equals( other.hospedagem ) ) return false;
		return true;
	}

	/**
	 * @return the hospedagem
	 */
	public Hospedagem getHospedagem()
	{
		return hospedagem;
	}

	/**
	 * @param hospedagem the hospedagem to set
	 */
	public void setHospedagem( Hospedagem hospedagem )
	{
		this.hospedagem = hospedagem;
	}
	
}
