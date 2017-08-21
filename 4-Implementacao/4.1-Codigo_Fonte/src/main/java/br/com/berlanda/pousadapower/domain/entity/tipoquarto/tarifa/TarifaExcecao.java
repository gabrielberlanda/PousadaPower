/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.util.Assert;

import br.com.berlanda.pousadapower.domain.entity.tipoquarto.TipoQuarto;
import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject ( javascript = "TarifaExcecao" )
public class TarifaExcecao extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4031018881495200267L;
	
	/**
	 * 
	 */
	@NotEmpty( message = "Nome é obrigatório")
	@Column(length=50, nullable = false )
	private String nome;
	
	/**
	 * 
	 */
	@NotNull( message= "Data inicial é obrigatório")
	@Column( nullable = false )
	private Calendar dataInicio;
	
	/**
	 * 
	 */
	@NotNull( message= "Data inicial é obrigatório")
	@Column( nullable = false )
	private Calendar dataFim;
	
	/**
	 * 
	 */
	@Size.List ({
	    @Size(min=1, message="A tarifa de execeção deve conter no minimo 1 tarifa"),
	    @Size(max=7, message="A tarifa de execeção deve conter no maximo 7 tarifa")
	})
	@NotNull( message = "Informe as tarifas")
	@OneToMany( fetch = FetchType.LAZY, cascade= CascadeType.ALL )
	@JoinColumn(name="tarifa_excecao_id")
	private Set<Tarifa> tarifas;
	
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.LAZY )
	private TipoQuarto tipoQuarto;

	/**
	 * 
	 */
	public TarifaExcecao()
	{
		super();
	}

	/**
	 * @param id
	 */
	public TarifaExcecao( Long id )
	{
		super( id );
	}

//	TarifaExcecao( tarifaExcecao.id, tarifaExcecao.nome, tarifaExcecao.dataInicio, tarifaExcecao.dataFim )"

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( dataFim == null ) ? 0 : dataFim.hashCode() );
		result = prime * result + ( ( dataInicio == null ) ? 0 : dataInicio.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
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
		TarifaExcecao other = ( TarifaExcecao ) obj;
		if ( dataFim == null )
		{
			if ( other.dataFim != null ) return false;
		}
		else if ( !dataFim.equals( other.dataFim ) ) return false;
		if ( dataInicio == null )
		{
			if ( other.dataInicio != null ) return false;
		}
		else if ( !dataInicio.equals( other.dataInicio ) ) return false;
		if ( nome == null )
		{
			if ( other.nome != null ) return false;
		}
		else if ( !nome.equals( other.nome ) ) return false;
		if ( tipoQuarto == null )
		{
			if ( other.tipoQuarto != null ) return false;
		}
		else if ( !tipoQuarto.equals( other.tipoQuarto ) ) return false;
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public TarifaExcecao validarDatas ()
	{
		Assert.isTrue( this.dataInicio.before( this.dataFim ) || this.dataInicio.equals( this.dataFim ), "Datas inválidas"  );
		return this;
	}
	
	/**
	 * 
	 */
	public TarifaExcecao validarTarifas()
	{
		final int MILLIS_IN_DAY = 86400000;
		final int quantidadeDia = Days.daysBetween( new DateTime( dataInicio ), new DateTime ( dataFim ) ).getDays();
		if ( quantidadeDia < Tarifa.QUANTIDADE_DIAS_SEMANA ) 
        {
        	Assert.isTrue( this.tarifas.size() == quantidadeDia+1 ); //É somado +1 pois é necessário a tarifa do primeiro dia também.
        	Tarifa.validarTarifasDeExcecao( this.tarifas, this.dataInicio, this.dataFim );
        } 
        else
        {
        	Assert.isTrue( this.tarifas.size() == Tarifa.QUANTIDADE_DIAS_SEMANA );
        	Tarifa.validarTarifaParaCadaDiaDaSemana( this.tarifas );
        }
        
		return this;
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
	 * @return the dataInicio
	 */
	public Calendar getDataInicio()
	{
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio( Calendar dataInicio )
	{
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public Calendar getDataFim()
	{
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim( Calendar dataFim )
	{
		this.dataFim = dataFim;
	}

	/**
	 * @return the tarifas
	 */
	public Set<Tarifa> getTarifas()
	{
		return tarifas;
	}

	/**
	 * @param tarifas the tarifas to set
	 */
	public void setTarifas( Set<Tarifa> tarifas )
	{
		this.tarifas = tarifas;
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
	
}
