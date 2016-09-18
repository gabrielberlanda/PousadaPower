/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.conta;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.util.Assert;

import br.com.berlanda.pousadapower.domain.entity.conta.pagamento.Pagamento;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject( javascript = "PagamentoHospedagem" )
public class PagamentoHospedagem extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3208349495726464348L;
	
	@NotNull
	@OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
	private Pagamento pagamento;
	
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER )
	private ContaHospedagem conta;
	
	private String justificativa;

	private boolean estornado;
	
	/**
	 * 
	 */
	public PagamentoHospedagem()
	{
		super();
	}

	/**
	 * @param id
	 */
	public PagamentoHospedagem( Long id )
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
		result = prime * result + ( ( conta == null ) ? 0 : conta.hashCode() );
		result = prime * result + ( estornado ? 1231 : 1237 );
		result = prime * result + ( ( justificativa == null ) ? 0 : justificativa.hashCode() );
		result = prime * result + ( ( pagamento == null ) ? 0 : pagamento.hashCode() );
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
		PagamentoHospedagem other = ( PagamentoHospedagem ) obj;
		if ( conta == null )
		{
			if ( other.conta != null ) return false;
		}
		else if ( !conta.equals( other.conta ) ) return false;
		if ( estornado != other.estornado ) return false;
		if ( justificativa == null )
		{
			if ( other.justificativa != null ) return false;
		}
		else if ( !justificativa.equals( other.justificativa ) ) return false;
		if ( pagamento == null )
		{
			if ( other.pagamento != null ) return false;
		}
		else if ( !pagamento.equals( other.pagamento ) ) return false;
		return true;
	}

	@PrePersist
	@PreUpdate
	public void validarJustificativaEstorno()
	{
		if ( this.estornado ) {
			Assert.notNull( this.justificativa, "Justifique seu estorno!" );
		}
	}

	/**
	 * @return the pagamento
	 */
	public Pagamento getPagamento()
	{
		return pagamento;
	}

	/**
	 * @param pagamento the pagamento to set
	 */
	public void setPagamento( Pagamento pagamento )
	{
		this.pagamento = pagamento;
	}

	/**
	 * @return the conta
	 */
	public ContaHospedagem getConta()
	{
		return conta;
	}

	/**
	 * @param conta the conta to set
	 */
	public void setConta( ContaHospedagem conta )
	{
		this.conta = conta;
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

	/**
	 * @return the estornado
	 */
	public boolean isEstornado()
	{
		return estornado;
	}

	/**
	 * @param estornado the estornado to set
	 */
	public void setEstornado( boolean estornado )
	{
		this.estornado = estornado;
	}
	
}
