/**
 * 
 */
package com.digows.blank.domain.entity.tipoquarto.tarifa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.util.Assert;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
@Entity
@DataTransferObject ( javascript = "Tarifa" )
public class Tarifa extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7940508173178417798L;
	
	public static final int QUANTIDADE_DIAS_SEMANA = 7;
	
	/**
	 * 
	 */
	@NotNull( message="Preço é obrigatório" )
	@Column( nullable=false )
	private Double preco;
	
	/**
	 * 
	 */
	@NotNull( message = "Dia é obrigatório" )
	@Enumerated( EnumType.ORDINAL)
	private Dia dia;

	/**
	 * 
	 */
	public Tarifa()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 */
	public Tarifa( Long id )
	{
		super( id );
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( dia == null ) ? 0 : dia.hashCode() );
		result = prime * result + ( ( preco == null ) ? 0 : preco.hashCode() );
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
		Tarifa other = ( Tarifa ) obj;
		if ( dia != other.dia ) return false;
		if ( preco == null )
		{
			if ( other.preco != null ) return false;
		}
		else if ( !preco.equals( other.preco ) ) return false;
		return true;
	}

	/**
	 * Validamos as tarifas, verificamos se possui as 7 tarifas da semana.
	 * @param tarifas
	 */
	public static void validarTarifaParaCadaDiaDaSemana( Set<Tarifa> tarifas )
	{
		Assert.isTrue( tarifas.size() == Tarifa.QUANTIDADE_DIAS_SEMANA );
		
		for( Dia dia : Dia.getDiasOrdenados() ) 
		{
			Assert.isTrue( Tarifa.possuiTarifaParaODia( dia, tarifas ), "O quarto não possui tarifa para todos os dias da semana" );
		}
	}
	
	/**
	 * 
	 */
	public static void validarTarifasDeExcecao( Set<Tarifa> tarifas, Calendar dataInicial, Calendar dataFinal)
	{
		Calendar _dataInicial = Calendar.getInstance();
		_dataInicial.setTime( dataInicial.getTime() );
        while (_dataInicial.get(Calendar.DAY_OF_MONTH) <= dataFinal.get(Calendar.DAY_OF_MONTH)){    
        	int diaIndex = _dataInicial.get( Calendar.DAY_OF_WEEK );
        	
        	Dia dia = Dia.getDiasOrdenados().get( diaIndex-1 );

        	Assert.isTrue( Tarifa.possuiTarifaParaODia( dia, tarifas ) );
        	
        	_dataInicial.add(Calendar.DAY_OF_MONTH, 1);    
        }    
	}
	
	/**
	 * 
	 * @param dia
	 * @param tarifas
	 * @return
	 */
	public static boolean possuiTarifaParaODia( Dia dia, Set<Tarifa> tarifas )
	{
		return tarifas.stream().filter( t -> t.getDia().equals( dia ) ).count() == 1;
	}
	
	/**
	 * @return the preco
	 */
	public Double getPreco()
	{
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco( Double preco )
	{
		this.preco = preco;
	}

	/**
	 * @return the dia
	 */
	public Dia getDia()
	{
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia( Dia dia )
	{
		this.dia = dia;
	}
	
}
