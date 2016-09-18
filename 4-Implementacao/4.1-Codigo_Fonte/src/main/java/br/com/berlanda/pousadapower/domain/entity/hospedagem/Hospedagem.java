/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.hospedagem;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.berlanda.pousadapower.domain.entity.conta.ContaHospedagem;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.TipoQuarto;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject( javascript= "Hospedagem" )
public class Hospedagem extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2331752523612587948L;
	
	@NotNull( message = "Informe uma data de entrada" )
	@Column( nullable = false )
	private Calendar entrada;
	
	@NotNull( message= "Informe uma data de saida" )
	@Column( nullable = false )
	private Calendar saida;
	
	@Column( length = 500 )
	private String observacao;

	@NotNull
	@Column( nullable = false, unique = true )
	private String codigo;
	
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	private TipoQuarto tipoQuarto;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private StatusHospedagem statusHospedagem;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL )
	private TipoHospedagem tipoHospedagem;

	@NotNull( message= "Informe um responsavel" )
	@OneToOne( cascade= CascadeType.ALL, fetch= FetchType.EAGER )
	private Responsavel responsavel;

	@OneToOne( fetch=FetchType.EAGER )
	private ContaHospedagem contaHospedagem;
	
	/**
	 * 
	 */
	public Hospedagem()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Hospedagem( Long id )
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
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( contaHospedagem == null ) ? 0 : contaHospedagem.hashCode() );
		result = prime * result + ( ( entrada == null ) ? 0 : entrada.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( responsavel == null ) ? 0 : responsavel.hashCode() );
		result = prime * result + ( ( saida == null ) ? 0 : saida.hashCode() );
		result = prime * result + ( ( statusHospedagem == null ) ? 0 : statusHospedagem.hashCode() );
		result = prime * result + ( ( tipoHospedagem == null ) ? 0 : tipoHospedagem.hashCode() );
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
		Hospedagem other = ( Hospedagem ) obj;
		if ( codigo == null )
		{
			if ( other.codigo != null ) return false;
		}
		else if ( !codigo.equals( other.codigo ) ) return false;
		if ( contaHospedagem == null )
		{
			if ( other.contaHospedagem != null ) return false;
		}
		else if ( !contaHospedagem.equals( other.contaHospedagem ) ) return false;
		if ( entrada == null )
		{
			if ( other.entrada != null ) return false;
		}
		else if ( !entrada.equals( other.entrada ) ) return false;
		if ( observacao == null )
		{
			if ( other.observacao != null ) return false;
		}
		else if ( !observacao.equals( other.observacao ) ) return false;
		if ( responsavel == null )
		{
			if ( other.responsavel != null ) return false;
		}
		else if ( !responsavel.equals( other.responsavel ) ) return false;
		if ( saida == null )
		{
			if ( other.saida != null ) return false;
		}
		else if ( !saida.equals( other.saida ) ) return false;
		if ( statusHospedagem != other.statusHospedagem ) return false;
		if ( tipoHospedagem != other.tipoHospedagem ) return false;
		return true;
	}
	
	
	public void generateCodigo()
	{
		this.codigo = "QWRQWRQWR";
	}

	/**
	 * @return the entrada
	 */
	public Calendar getEntrada()
	{
		return entrada;
	}

	/**
	 * @param entrada the entrada to set
	 */
	public void setEntrada( Calendar entrada )
	{
		this.entrada = entrada;
	}

	/**
	 * @return the saida
	 */
	public Calendar getSaida()
	{
		return saida;
	}

	/**
	 * @param saida the saida to set
	 */
	public void setSaida( Calendar saida )
	{
		this.saida = saida;
	}

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
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	/**
	 * @return the statusHospedagem
	 */
	public StatusHospedagem getStatusHospedagem()
	{
		return statusHospedagem;
	}

	/**
	 * @param statusHospedagem the statusHospedagem to set
	 */
	public void setStatusHospedagem( StatusHospedagem statusHospedagem )
	{
		this.statusHospedagem = statusHospedagem;
	}

	/**
	 * @return the tipoHospedagem
	 */
	public TipoHospedagem getTipoHospedagem()
	{
		return tipoHospedagem;
	}

	/**
	 * @param tipoHospedagem the tipoHospedagem to set
	 */
	public void setTipoHospedagem( TipoHospedagem tipoHospedagem )
	{
		this.tipoHospedagem = tipoHospedagem;
	}

	/**
	 * @return the responsavel
	 */
	public Responsavel getResponsavel()
	{
		return responsavel;
	}

	/**
	 * @param responsavel the responsavel to set
	 */
	public void setResponsavel( Responsavel responsavel )
	{
		this.responsavel = responsavel;
	}

	/**
	 * @return the contaHospedagem
	 */
	public ContaHospedagem getContaHospedagem()
	{
		return contaHospedagem;
	}

	/**
	 * @param contaHospedagem the contaHospedagem to set
	 */
	public void setContaHospedagem( ContaHospedagem contaHospedagem )
	{
		this.contaHospedagem = contaHospedagem;
	}
	
}
