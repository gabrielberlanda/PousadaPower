/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.conta;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.util.Assert;

import br.com.berlanda.pousadapower.domain.entity.hospedagem.OrcamentoDiaria;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject( javascript = "DiariaHospedagem" )
public class DiariaHospedagem extends AbstractEntity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2228685745516929775L;
	
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	private ContaHospedagem contaHospede;
	
	@ManyToOne ( fetch = FetchType.LAZY, optional = false )
	private OrcamentoDiaria diaria;
	
	private boolean estonardo;
	
	@Column( nullable = true, length = 500 )
	private String justificativa;
	
	/**
	 * 
	 */
	public DiariaHospedagem()
	{
		super();
	}

	/**
	 * @param id
	 */
	public DiariaHospedagem( Long id )
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
		result = prime * result + ( ( contaHospede == null ) ? 0 : contaHospede.hashCode() );
		result = prime * result + ( ( diaria == null ) ? 0 : diaria.hashCode() );
		result = prime * result + ( estonardo ? 1231 : 1237 );
		result = prime * result + ( ( justificativa == null ) ? 0 : justificativa.hashCode() );
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
		DiariaHospedagem other = ( DiariaHospedagem ) obj;
		if ( contaHospede == null )
		{
			if ( other.contaHospede != null ) return false;
		}
		else if ( !contaHospede.equals( other.contaHospede ) ) return false;
		if ( diaria == null )
		{
			if ( other.diaria != null ) return false;
		}
		else if ( !diaria.equals( other.diaria ) ) return false;
		if ( estonardo != other.estonardo ) return false;
		if ( justificativa == null )
		{
			if ( other.justificativa != null ) return false;
		}
		else if ( !justificativa.equals( other.justificativa ) ) return false;
		return true;
	}

	@PrePersist
	@PreUpdate
	public void validarJustificativa() 
	{
		if ( this.estonardo )
		{
			Assert.notNull( this.justificativa, "Justifique o estorno da diaria!" );
		}
	}

	/**
	 * @return the contaHospede
	 */
	public ContaHospedagem getContaHospede()
	{
		return contaHospede;
	}

	/**
	 * @param contaHospede the contaHospede to set
	 */
	public void setContaHospede( ContaHospedagem contaHospede )
	{
		this.contaHospede = contaHospede;
	}

	/**
	 * @return the diaria
	 */
	public OrcamentoDiaria getDiaria()
	{
		return diaria;
	}

	/**
	 * @param diaria the diaria to set
	 */
	public void setDiaria( OrcamentoDiaria diaria )
	{
		this.diaria = diaria;
	}

	/**
	 * @return the estonardo
	 */
	public boolean isEstonardo()
	{
		return estonardo;
	}

	/**
	 * @param estonardo the estonardo to set
	 */
	public void setEstonardo( boolean estonardo )
	{
		this.estonardo = estonardo;
	}

	/**
	 * @return the justificativa
	 */
	public String getJustificativa()
	{
		return justificativa;
	}

	/**
	 * @param justificativa the justificativa to set
	 */
	public void setJustificativa( String justificativa )
	{
		this.justificativa = justificativa;
	}
	
}
