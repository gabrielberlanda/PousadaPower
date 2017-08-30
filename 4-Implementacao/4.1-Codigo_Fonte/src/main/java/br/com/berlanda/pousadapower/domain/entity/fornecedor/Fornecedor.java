/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.fornecedor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.berlanda.pousadapower.domain.entity.pessoa.Cidade;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Berlanda
 *
 */
@Entity
@DataTransferObject( javascript = "Fornecedor" ) 
public class Fornecedor extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5835549186119047613L;

	/**
	 * 
	 */
	@NotEmpty( message ="Nome fantasia é obrigatório." )
	@Column( length= 50, nullable = false, unique = true )
	private String nomeFantasia;

	/**
	 * 
	 */
	@NotEmpty( message = "Razão social é obrigatório.")
	@Column( length= 50, nullable = false, unique = true )
	private String razaoSocial;
	
	/**
	 * 
	 */
	@NotEmpty( message= "Email é obrigatório." )
	@Column( length= 100, nullable = false, unique = true )
	private String email;
	
	/**
	 * 
	 */
	@NotEmpty( message = "Telefone é obrigatório.")
	@Column( length= 50, nullable = false )
	private String telefone;
	
	/**
	 * 
	 */
	@Column ( length= 30 )
	private String inscricaoEstadual;

	/**
	 * 
	 */
	@NotEmpty( message="CNPJ é obrigatório." )
	@Column( length= 14, nullable = false, unique = true )
	private String cnpj;
	
	/**
	 * 
	 */
	@ManyToOne( fetch = FetchType.LAZY )
	private Cidade cidade;
	
	/**
	 * 
	 */
	@Column( length = 250 )
	private String complemento;
	
	/**
	 * 
	 */
	@Column ( length = 50 )
	private String bairro;
	
	/**
	 * 
	 */
	@Column( nullable = true, length= 50 )
	private String cep;
	
	/**
	 * 
	 */
	private Integer numero;
	
	/**
	 * 
	 */
	@Column ( length = 500 )
	private String observacao;

	/**
	 * 
	 */
	public Fornecedor()
	{
		super();
	}
	
	/**
	 * @param id
	 */
	public Fornecedor( Long id )
	{
		super( id );
	}
	
	/**
	 * @param nomeFantasia
	 * @param razaoSocial
	 * @param email
	 * @param telefone
	 * @param inscricaoEstadual
	 * @param cnpj
	 * @param cidade
	 * @param endereco
	 * @param bairro
	 * @param numero
	 * @param observacao
	 */
	public Fornecedor( String nomeFantasia, String razaoSocial, String email, String telefone, String inscricaoEstadual, String cnpj, Cidade cidade, String endereco, String bairro, Integer numero, String observacao )
	{
		super();
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.email = email;
		this.telefone = telefone;
		this.inscricaoEstadual = inscricaoEstadual;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.bairro = bairro;
		this.numero = numero;
		this.observacao = observacao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( bairro == null ) ? 0 : bairro.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( cnpj == null ) ? 0 : cnpj.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( inscricaoEstadual == null ) ? 0 : inscricaoEstadual.hashCode() );
		result = prime * result + ( ( nomeFantasia == null ) ? 0 : nomeFantasia.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( razaoSocial == null ) ? 0 : razaoSocial.hashCode() );
		result = prime * result + ( ( telefone == null ) ? 0 : telefone.hashCode() );
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
		Fornecedor other = ( Fornecedor ) obj;
		if ( bairro == null )
		{
			if ( other.bairro != null ) return false;
		}
		else if ( !bairro.equals( other.bairro ) ) return false;
		if ( cep == null )
		{
			if ( other.cep != null ) return false;
		}
		else if ( !cep.equals( other.cep ) ) return false;
		if ( cidade == null )
		{
			if ( other.cidade != null ) return false;
		}
		else if ( !cidade.equals( other.cidade ) ) return false;
		if ( cnpj == null )
		{
			if ( other.cnpj != null ) return false;
		}
		else if ( !cnpj.equals( other.cnpj ) ) return false;
		if ( complemento == null )
		{
			if ( other.complemento != null ) return false;
		}
		else if ( !complemento.equals( other.complemento ) ) return false;
		if ( email == null )
		{
			if ( other.email != null ) return false;
		}
		else if ( !email.equals( other.email ) ) return false;
		if ( inscricaoEstadual == null )
		{
			if ( other.inscricaoEstadual != null ) return false;
		}
		else if ( !inscricaoEstadual.equals( other.inscricaoEstadual ) ) return false;
		if ( nomeFantasia == null )
		{
			if ( other.nomeFantasia != null ) return false;
		}
		else if ( !nomeFantasia.equals( other.nomeFantasia ) ) return false;
		if ( numero == null )
		{
			if ( other.numero != null ) return false;
		}
		else if ( !numero.equals( other.numero ) ) return false;
		if ( observacao == null )
		{
			if ( other.observacao != null ) return false;
		}
		else if ( !observacao.equals( other.observacao ) ) return false;
		if ( razaoSocial == null )
		{
			if ( other.razaoSocial != null ) return false;
		}
		else if ( !razaoSocial.equals( other.razaoSocial ) ) return false;
		if ( telefone == null )
		{
			if ( other.telefone != null ) return false;
		}
		else if ( !telefone.equals( other.telefone ) ) return false;
		return true;
	}


	/**
	 * @return the nomeFantasia
	 */
	public String getNomeFantasia()
	{
		return nomeFantasia;
	}

	/**
	 * @param nomeFantasia the nomeFantasia to set
	 */
	public void setNomeFantasia( String nomeFantasia )
	{
		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * @return the razaoSocial
	 */
	public String getRazaoSocial()
	{
		return razaoSocial;
	}

	/**
	 * @param razaoSocial the razaoSocial to set
	 */
	public void setRazaoSocial( String razaoSocial )
	{
		this.razaoSocial = razaoSocial;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail( String email )
	{
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone()
	{
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone( String telefone )
	{
		this.telefone = telefone;
	}

	/**
	 * @return the inscricaoEstadual
	 */
	public String getInscricaoEstadual()
	{
		return inscricaoEstadual;
	}

	/**
	 * @param inscricaoEstadual the inscricaoEstadual to set
	 */
	public void setInscricaoEstadual( String inscricaoEstadual )
	{
		this.inscricaoEstadual = inscricaoEstadual;
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj()
	{
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj( String cnpj )
	{
		this.cnpj = cnpj;
	}

	/**
	 * @return the cidade
	 */
	public Cidade getCidade()
	{
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade( Cidade cidade )
	{
		this.cidade = cidade;
	}



	/**
	 * @return the complemento
	 */
	public String getComplemento()
	{
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento( String complemento )
	{
		this.complemento = complemento;
	}

	/**
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep( String cep )
	{
		this.cep = cep;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro()
	{
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro( String bairro )
	{
		this.bairro = bairro;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero()
	{
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero( Integer numero )
	{
		this.numero = numero;
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

}
