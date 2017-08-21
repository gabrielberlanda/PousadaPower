/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.conta;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.berlanda.pousadapower.domain.entity.produto.Produto;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject( javascript = "ProdutoConsumido" )
public class ProdutoConsumido extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8714908595957907125L;
	
	@Min( value = 1, message = "A quantidade minima é 1" )
	@NotNull( message= "Informe a quantidade" )
	@Column( nullable = false )
	private Integer quantidade;
	
	private boolean estornado;
	
	@Min( value = 0, message = "O preço minimo é R$ 0,00" )
	@NotNull( message = "Informe o preço unitário" )
	@Column( nullable = false )
	private Double precoUnitario;
	
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	private ContaHospedagem conta;
	
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	private Produto produto;

	/**
	 * 
	 */
	public ProdutoConsumido()
	{
		super();
	}

	/**
	 * @param id
	 */
	public ProdutoConsumido( Long id )
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
		result = prime * result + ( ( precoUnitario == null ) ? 0 : precoUnitario.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
		result = prime * result + ( ( quantidade == null ) ? 0 : quantidade.hashCode() );
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
		ProdutoConsumido other = ( ProdutoConsumido ) obj;
		if ( conta == null )
		{
			if ( other.conta != null ) return false;
		}
		else if ( !conta.equals( other.conta ) ) return false;
		if ( estornado != other.estornado ) return false;
		if ( precoUnitario == null )
		{
			if ( other.precoUnitario != null ) return false;
		}
		else if ( !precoUnitario.equals( other.precoUnitario ) ) return false;
		if ( produto == null )
		{
			if ( other.produto != null ) return false;
		}
		else if ( !produto.equals( other.produto ) ) return false;
		if ( quantidade == null )
		{
			if ( other.quantidade != null ) return false;
		}
		else if ( !quantidade.equals( other.quantidade ) ) return false;
		return true;
	}

	/**
	 * @return the quantidade
	 */
	public Integer getQuantidade()
	{
		return quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade( Integer quantidade )
	{
		this.quantidade = quantidade;
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

	/**
	 * @return the precoUnitario
	 */
	public Double getPrecoUnitario()
	{
		return precoUnitario;
	}

	/**
	 * @param precoUnitario the precoUnitario to set
	 */
	public void setPrecoUnitario( Double precoUnitario )
	{
		this.precoUnitario = precoUnitario;
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
	 * @return the produto
	 */
	public Produto getProduto()
	{
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto( Produto produto )
	{
		this.produto = produto;
	}
	
}
