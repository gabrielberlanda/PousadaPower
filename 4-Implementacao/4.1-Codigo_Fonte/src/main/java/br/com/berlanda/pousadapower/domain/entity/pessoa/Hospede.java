/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.pessoa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author BERLANDA
 *
 */
@Entity
@DiscriminatorValue( value = Pessoa.HOSPEDE_VALUE )
@DataTransferObject( javascript = "Hospede" )
public class Hospede extends Pessoa implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7546938978181416165L;

	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	private String modeloVeiculo;

	/**
	 * 
	 */
	private String placa;
	
	/**
	 * 
	 */
	@Column( length = 500 )
	private String observacao;

	/*-------------------------------------------------------------------
	* 		 					CONSTRUTORES
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Hospede()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Hospede( Long id )
	{
		super( id );
	}
	
	/**
	 * 
	 * @param id
	 * @param nome
	 * @param email
	 * @param telefone
	 * @param cidade
	 */
	public Hospede( Long id, String nome, String email, String telefone, Cidade cidade )
	{
		super ( id );
		this.setNome( nome );
		this.setEmail( email );
		this.setTelefone( telefone );
		
		this.setCidade( cidade );
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
		result = prime * result + ( ( modeloVeiculo == null ) ? 0 : modeloVeiculo.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( placa == null ) ? 0 : placa.hashCode() );
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
		Hospede other = ( Hospede ) obj;
		if ( modeloVeiculo == null )
		{
			if ( other.modeloVeiculo != null ) return false;
		}
		else if ( !modeloVeiculo.equals( other.modeloVeiculo ) ) return false;
		if ( observacao == null )
		{
			if ( other.observacao != null ) return false;
		}
		else if ( !observacao.equals( other.observacao ) ) return false;
		if ( placa == null )
		{
			if ( other.placa != null ) return false;
		}
		else if ( !placa.equals( other.placa ) ) return false;
		return true;
	}

	/*-------------------------------------------------------------------
	*						GETTERS AND SETTERS
	*-------------------------------------------------------------------*/

	/**
	 * @return the observacao
	 */
	public String getObservacao()
	{
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao( String observacao )
	{
		this.observacao = observacao;
	}

	/**
	 * @return the modeloVeiculo
	 */
	public String getModeloVeiculo()
	{
		return modeloVeiculo;
	}

	/**
	 * @param modeloVeiculo the modeloVeiculo to set
	 */
	public void setModeloVeiculo( String modeloVeiculo )
	{
		this.modeloVeiculo = modeloVeiculo;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca()
	{
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca( String placa )
	{
		this.placa = placa;
	}
	
	
}
