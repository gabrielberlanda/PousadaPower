/**
 * 
 */
package com.digows.blank.domain.entity.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.util.Assert;

import com.digows.blank.domain.entity.fornecedor.Fornecedor;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Berlanda
 *
 */
@Entity
@DataTransferObject ( javascript = "Produto" )
public class Produto extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1497264977683051122L;
	
	/**
	 * 
	 */
	@Column( length = 50, nullable = false, unique = true )
	private String nome;
	
	/**
	 * 
	 */
	@Column( length = 200 )
	private String descricao;
	
	/**
	 * 
	 */
	@Column( nullable = false )
	private Integer quantidadeEstoque;
	
	/**
	 * 
	 */
	@Column ( nullable = false )
	private Integer quantidadeMinima;
	
	/**
	 * 
	 */
	@ManyToOne( optional = false, fetch = FetchType.EAGER )
	private Fornecedor fornecedor;
	
	/**
	 * 
	 */
	@Column( nullable = false )
	private Double precoUnitario;

	/**
	 * 
	 */
	@Enumerated( EnumType.ORDINAL )
	private TipoProduto tipoProduto;
	/**
	 * 
	 */
	@Column ( nullable = false )
	private Double precoCusto;
	
	/**
	 * 
	 */
	public Produto()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Produto( Long id )
	{
		super( id );
	}
	
	public Produto( Long id, String nome, Integer quantidadeEstoque, Integer quantidadeMinima, 
			Double precoUnitario, Double precoCusto, TipoProduto tipoProduto, Long fornecedorId, String razaoSocial, String nomeFantasia )
	{
		super ( id );
		this.nome = nome;
		this.quantidadeEstoque = quantidadeEstoque;
		this.quantidadeMinima = quantidadeMinima;
		this.precoUnitario = precoUnitario;
		this.precoCusto = precoCusto;
		this.tipoProduto = tipoProduto;
	
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId( fornecedorId );
		fornecedor.setRazaoSocial( razaoSocial );
		fornecedor.setNomeFantasia( nomeFantasia );
		
		this.fornecedor = fornecedor;
	}

	/**
	 * @param nome
	 * @param descricao
	 * @param quantidadeEstoque
	 * @param quantidadeMinima
	 * @param precoUnitario
	 * @param precoCusto
	 */
	public Produto( String nome, String descricao, Integer quantidadeEstoque, Integer quantidadeMinima, Double precoUnitario, Double precoCusto )
	{
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.quantidadeEstoque = quantidadeEstoque;
		this.quantidadeMinima = quantidadeMinima;
		this.precoUnitario = precoUnitario;
		this.precoCusto = precoCusto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( precoCusto == null ) ? 0 : precoCusto.hashCode() );
		result = prime * result + ( ( precoUnitario == null ) ? 0 : precoUnitario.hashCode() );
		result = prime * result + ( ( quantidadeEstoque == null ) ? 0 : quantidadeEstoque.hashCode() );
		result = prime * result + ( ( quantidadeMinima == null ) ? 0 : quantidadeMinima.hashCode() );
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
		Produto other = ( Produto ) obj;
		if ( descricao == null )
		{
			if ( other.descricao != null ) return false;
		}
		else if ( !descricao.equals( other.descricao ) ) return false;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( precoCusto == null )
		{
			if ( other.precoCusto != null ) return false;
		}
		else if ( !precoCusto.equals( other.precoCusto ) ) return false;
		if ( precoUnitario == null )
		{
			if ( other.precoUnitario != null ) return false;
		}
		else if ( !precoUnitario.equals( other.precoUnitario ) ) return false;
		if ( quantidadeEstoque == null )
		{
			if ( other.quantidadeEstoque != null ) return false;
		}
		else if ( !quantidadeEstoque.equals( other.quantidadeEstoque ) ) return false;
		if ( quantidadeMinima == null )
		{
			if ( other.quantidadeMinima != null ) return false;
		}
		else if ( !quantidadeMinima.equals( other.quantidadeMinima ) ) return false;
		return true;
	}
	
	/**
	 * Valida os campos de produto
	 */
	public void validarCampos ()
	{
		Assert.notNull(  this.nome, "Campo nome é obrigatório" );
		Assert.notNull(  this.precoCusto, "Campo preço de custo é obrigatório" );
		Assert.notNull(  this.precoUnitario, "Campo preço unitário é obrigatório" );
		Assert.notNull(  this.quantidadeEstoque, "Campo quantidade no estoque é obrigatório" );
		Assert.notNull(  this.quantidadeMinima, "Campo quantidade miníma é obrigatório" );
		Assert.notNull(  this.fornecedor, "Informe um fornecedor" );
		
		Assert.isTrue( this.quantidadeMinima >= 0, "Quantidade miníma não pode ser negativo" );
		Assert.isTrue( this.quantidadeEstoque >= 0, "Quantidade no estoque não pode ser negativo" );
		
		Assert.isTrue( this.precoCusto >= 0 , "Preço de custo não pode ser negativo" );
		Assert.isTrue( this.precoUnitario >= 0, "Preço unitário não pode ser negativo" );
	}

	/**
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome( String nome )
	{
		this.nome = nome;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	/**
	 * @return the quantidadeEstoque
	 */
	public Integer getQuantidadeEstoque()
	{
		return quantidadeEstoque;
	}

	/**
	 * @param quantidadeEstoque the quantidadeEstoque to set
	 */
	public void setQuantidadeEstoque( Integer quantidadeEstoque )
	{
		this.quantidadeEstoque = quantidadeEstoque;
	}

	/**
	 * @return the quantidadeMinima
	 */
	public Integer getQuantidadeMinima()
	{
		return quantidadeMinima;
	}

	/**
	 * @param quantidadeMinima the quantidadeMinima to set
	 */
	public void setQuantidadeMinima( Integer quantidadeMinima )
	{
		this.quantidadeMinima = quantidadeMinima;
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
	 * @return the precoCusto
	 */
	public Double getPrecoCusto()
	{
		return precoCusto;
	}

	/**
	 * @param precoCusto the precoCusto to set
	 */
	public void setPrecoCusto( Double precoCusto )
	{
		this.precoCusto = precoCusto;
	}

	/**
	 * @return the fornecedor
	 */
	public Fornecedor getFornecedor()
	{
		return fornecedor;
	}

	/**
	 * @param fornecedor the fornecedor to set
	 */
	public void setFornecedor( Fornecedor fornecedor )
	{
		this.fornecedor = fornecedor;
	}

	/**
	 * @return the tipoProduto
	 */
	public TipoProduto getTipoProduto()
	{
		return tipoProduto;
	}

	/**
	 * @param tipoProduto the tipoProduto to set
	 */
	public void setTipoProduto( TipoProduto tipoProduto )
	{
		this.tipoProduto = tipoProduto;
	}
	
}
