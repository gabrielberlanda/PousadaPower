/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.conta.pagamento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.util.Assert;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject( javascript = "Pagamento" )
public class Pagamento extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4500390548023586020L;
	
	/**
	 * 
	 */
	@NotNull(message="O valor não pode ser nulo")
	@Column( nullable = false )
	private Double valor;
	
	/**
	 * 
	 */
	@NotNull(message="Informe um tipo de pagamento")
	@Enumerated( EnumType.ORDINAL )
	private TipoPagamento tipoPagamento;
	/**
	 * 
	 */
	@Enumerated( EnumType.ORDINAL )
	private BandeiraCartao bandeiraCartao;

	/**
	 * 
	 */
	public Pagamento()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Pagamento( Long id )
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
		result = prime * result + ( ( bandeiraCartao == null ) ? 0 : bandeiraCartao.hashCode() );
		result = prime * result + ( ( tipoPagamento == null ) ? 0 : tipoPagamento.hashCode() );
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
		Pagamento other = ( Pagamento ) obj;
		if ( bandeiraCartao != other.bandeiraCartao ) return false;
		if ( tipoPagamento != other.tipoPagamento ) return false;
		if ( valor == null )
		{
			if ( other.valor != null ) return false;
		}
		else if ( !valor.equals( other.valor ) ) return false;
		return true;
	}
	
	/**
	 * Valida a entidade de pagamento
	 */
	@PreUpdate
	@PrePersist
	public void validarPagamento()
	{
		validarValor();
		validarBandeiraCartao();
	}
	
	/**
	 * Valida o valor minimo do pagamento
	 */
	public void validarValor() 
	{
		Assert.isTrue( this.valor > 0.01, "O valor deve ser maior a 0.01" );
	}
	
	/**
	 * A bandeira do cartão se torna obrigatória caso o tipo de pagamento for credito ou debito
	 */
	public void validarBandeiraCartao()
	{
		if ( this.tipoPagamento.equals(TipoPagamento.CREDITO) || ( this.tipoPagamento.equals( TipoPagamento.DEBITO ) )  ) {
			Assert.notNull( this.bandeiraCartao, "Informe a bandeira do cartão" );
		}
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

	/**
	 * @return the tipoPagamento
	 */
	public TipoPagamento getTipoPagamento()
	{
		return tipoPagamento;
	}

	/**
	 * @param tipoPagamento the tipoPagamento to set
	 */
	public void setTipoPagamento( TipoPagamento tipoPagamento )
	{
		this.tipoPagamento = tipoPagamento;
	}

	/**
	 * @return the bandeiraCartao
	 */
	public BandeiraCartao getBandeiraCartao()
	{
		return bandeiraCartao;
	}

	/**
	 * @param bandeiraCartao the bandeiraCartao to set
	 */
	public void setBandeiraCartao( BandeiraCartao bandeiraCartao )
	{
		this.bandeiraCartao = bandeiraCartao;
	}

}
