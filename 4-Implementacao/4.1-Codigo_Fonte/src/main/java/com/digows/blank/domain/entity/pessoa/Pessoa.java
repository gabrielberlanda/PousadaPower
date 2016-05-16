/**
 * 
 */
package com.digows.blank.domain.entity.pessoa;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.junit.Assert;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Gabriel Berlanda
 *
 */
@Entity
@DataTransferObject(javascript = "Pessoa")
@DiscriminatorColumn(name="tipo")
public class Pessoa extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5295265388190591451L;
	
	/**
	 * 
	 */
	public static final String HOSPEDE_VALUE = "HOSPEDE";
	
	/**
	 * 
	 */
	public static final String FORNECEDOR_VALUE = "FORNECEDOR";
	
	
	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Column( nullable = false, length = 50 )
	private String nome;
	
	/**
	 * 
	 */
	@Column ( unique = true, length = 9 )
	private String rg;
	
	/**
	 * 
	 */
	@Column( unique = true, length = 11 )
	private String cpf;

	/**
	 * 
	 */
	@Column ( unique = true, length = 20 )
	private String passaporte;
	
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataNascimento;
	
	/**
	 * 
	 */
	@Email
	@Column( nullable = false, length = 100 )
	private String email;
	
	/**
	 * 
	 */
	@Column( nullable = false, length = 25 )
	private String telefone;
	
	/**
	 * 
	 */
	@Column( nullable = true, length = 25 )
	private String celular;
	
	/**
	 * 
	 */
	@Column( nullable = true, length= 50 )
	private String cep;
	
	/**
	 * 
	 */
	@Column( nullable = true, length = 50 )
	private String bairro;
	
	/**
	 * 
	 */
	@Column( nullable = true, length = 100 )
	private String complemento;
	
	/**
	 * 
	 */
	@Column( nullable = true )
	private Integer numero;
	
	/**
	 * 
	 */
	@ManyToOne( optional = true, fetch = FetchType.EAGER )
	private Cidade cidade;
	
	/**
	 * 
	 */
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;

	/*-------------------------------------------------------------------
	* 		 					CONSTRUTORES
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	public Pessoa()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Pessoa( Long id )
	{
		super( id );
	}
	 
	/*-------------------------------------------------------------------
	*							METODOS
	*-------------------------------------------------------------------*/
	
	public void validarPessoa()
	{
		Assert.assertNotNull( "Campo nome não pode ser nulo" , this.nome );
		Assert.assertNotNull( "Campo email não pode ser nulo", this.email );
		Assert.assertNotNull( "Campo telefone não pode ser Nulo", this.telefone );
		
		if ( this.getCidade() != null )
		{
			if ( this.getCidade().getEstado().getPais() != null )
			{
				
			}
		}
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
		result = prime * result + ( ( celular == null ) ? 0 : celular.hashCode() );
		result = prime * result + ( ( cep == null ) ? 0 : cep.hashCode() );
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
		result = prime * result + ( ( complemento == null ) ? 0 : complemento.hashCode() );
		result = prime * result + ( ( cpf == null ) ? 0 : cpf.hashCode() );
		result = prime * result + ( ( dataNascimento == null ) ? 0 : dataNascimento.hashCode() );
		result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( passaporte == null ) ? 0 : passaporte.hashCode() );
		result = prime * result + ( ( rg == null ) ? 0 : rg.hashCode() );
		result = prime * result + ( ( sexo == null ) ? 0 : sexo.hashCode() );
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
		Pessoa other = ( Pessoa ) obj;
		if ( bairro == null )
		{
			if ( other.bairro != null ) return false;
		}
		else if ( !bairro.equals( other.bairro ) ) return false;
		if ( celular == null )
		{
			if ( other.celular != null ) return false;
		}
		else if ( !celular.equals( other.celular ) ) return false;
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
		if ( complemento == null )
		{
			if ( other.complemento != null ) return false;
		}
		else if ( !complemento.equals( other.complemento ) ) return false;
		if ( cpf == null )
		{
			if ( other.cpf != null ) return false;
		}
		else if ( !cpf.equals( other.cpf ) ) return false;
		if ( dataNascimento == null )
		{
			if ( other.dataNascimento != null ) return false;
		}
		else if ( !dataNascimento.equals( other.dataNascimento ) ) return false;
		if ( email == null )
		{
			if ( other.email != null ) return false;
		}
		else if ( !email.equals( other.email ) ) return false;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( numero == null )
		{
			if ( other.numero != null ) return false;
		}
		else if ( !numero.equals( other.numero ) ) return false;
		if ( passaporte == null )
		{
			if ( other.passaporte != null ) return false;
		}
		else if ( !passaporte.equals( other.passaporte ) ) return false;
		if ( rg == null )
		{
			if ( other.rg != null ) return false;
		}
		else if ( !rg.equals( other.rg ) ) return false;
		if ( sexo != other.sexo ) return false;
		if ( telefone == null )
		{
			if ( other.telefone != null ) return false;
		}
		else if ( !telefone.equals( other.telefone ) ) return false;
		return true;
	}

	/*-------------------------------------------------------------------
	*						GETTERS AND SETTERS
	*-------------------------------------------------------------------*/
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return nome;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name )
	{
		this.nome = name;
	}

	/**
	 * @return the rg
	 */
	public String getRg()
	{
		return rg;
	}

	/**
	 * @param rg the rg to set
	 */
	public void setRg( String rg )
	{
		this.rg = rg;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf()
	{
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf( String cpf )
	{
		this.cpf = cpf;
	}

	/**
	 * @return the passaporte
	 */
	public String getPassaporte()
	{
		return passaporte;
	}

	/**
	 * @param passaporte the passaporte to set
	 */
	public void setPassaporte( String passaporte )
	{
		this.passaporte = passaporte;
	}

	/**
	 * @return the dataNascimento
	 */
	public Calendar getDataNascimento()
	{
		return dataNascimento;
	}

	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento( Calendar dataNascimento )
	{
		this.dataNascimento = dataNascimento;
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
	 * @return the celular
	 */
	public String getCelular()
	{
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular( String celular )
	{
		this.celular = celular;
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
	 * @return the sexo
	 */
	public Sexo getSexo()
	{
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo( Sexo sexo )
	{
		this.sexo = sexo;
	}
	
}
