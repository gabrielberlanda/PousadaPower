/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.quarto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FilenameUtils;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.io.FileTransfer;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.Assert;

import br.com.berlanda.pousadapower.domain.entity.tipoquarto.TipoQuarto;
import br.com.eits.common.application.i18n.ResourceBundleMessageSource;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject( javascript = "Quarto" )
public class Quarto extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5942797634571756151L;
	
	/**
	 * 
	 */
	public static final String QUARTO_FILE_FOLDER = "/quarto/%d/file";

	/**
	 * 
	 */
	public static final List<String> validImagesMimeTypes = new ArrayList<String>();
	
	static
	{
		//IMAGENS
		validImagesMimeTypes.add("image/gif");
		validImagesMimeTypes.add("image/jpeg");
		validImagesMimeTypes.add("image/jpg");
		validImagesMimeTypes.add("image/bmp");
		validImagesMimeTypes.add("image/png");
	}
	
	//Atributos
	@NotEmpty(message="Nome é obrigatório")
	@Column( length = 50, nullable = false, unique = true )
	private String nome;
	
	@Column( length = 500 )
	private String observacao;
	
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER, optional = false )
	private TipoQuarto tipoQuarto;
	
	@NotNull
	@Enumerated( EnumType.ORDINAL)
	private StatusQuarto status;
	
	private Boolean ativo;
	
	/**
	 * 
	 */
	public Quarto()
	{
		super();
	}

	/**
	 * @param id
	 */
	public Quarto( Long id )
	{
		super( id );
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 * @param tipoQuartoId
	 * @param tipoQuartoNome
	 * @param status
	 * @param ativo
	 */
	public Quarto( Long id, String nome, Long tipoQuartoId, String tipoQuartoNome, StatusQuarto status, Boolean ativo )
	{
		super(id);
		this.nome = nome;
		
		TipoQuarto tipoQuarto = new TipoQuarto();
		tipoQuarto.setId( tipoQuartoId );
		tipoQuarto.setNome( tipoQuartoNome );
		
		this.tipoQuarto = tipoQuarto;
		this.status = status;
		this.ativo = ativo;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		result = prime * result + ( ( tipoQuarto == null ) ? 0 : tipoQuarto.hashCode() );
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
		Quarto other = ( Quarto ) obj;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( observacao == null )
		{
			if ( other.observacao != null ) return false;
		}
		else if ( !observacao.equals( other.observacao ) ) return false;
		if ( status != other.status ) return false;
		if ( tipoQuarto == null )
		{
			if ( other.tipoQuarto != null ) return false;
		}
		else if ( !tipoQuarto.equals( other.tipoQuarto ) ) return false;
		return true;
	}

	/**
	 * 
	 * @param photo
	 * @param messageSource
	 */
	public void isValidFile ( FileTransfer photo )
	{
		Assert.isTrue( Quarto.validImagesMimeTypes.contains( photo.getMimeType() ), "Tipo de Arquivo inválido" );
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
	 * @return the tipoQuarto
	 */
	public TipoQuarto getTipoQuarto()
	{
		return tipoQuarto;
	}

	/**
	 * @param tipoQuarto the tipoQuarto to set
	 */
	public void setTipoQuarto( TipoQuarto tipoQuarto )
	{
		this.tipoQuarto = tipoQuarto;
	}

	/**
	 * @return the status
	 */
	public StatusQuarto getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus( StatusQuarto status )
	{
		this.status = status;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo()
	{
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}
	
	public String getPath()
	{
		return String.format( Quarto.QUARTO_FILE_FOLDER+"/%d", this.id, this.id );
	}
	
	
}
