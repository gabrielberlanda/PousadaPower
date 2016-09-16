/**
 * 
 */
package com.digows.blank.test.domain.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.digows.blank.test.domain.AbstractUnitTests;

import br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa.Dia;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa.Tarifa;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa.TarifaExcecao;

/**
 * @author berlanda
 *
 */
public class TarifaExcecaoTests extends AbstractUnitTests
{
	/**
	 * 
	 */
	@Test
	public void validarDatasMustPass()
	{
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		
		tarifaExcecao.setDataInicio( Calendar.getInstance() );
		tarifaExcecao.setDataFim( Calendar.getInstance() );
		
		tarifaExcecao.getDataInicio().set( Calendar.DAY_OF_MONTH, 20 );
		tarifaExcecao.getDataFim().set( Calendar.DAY_OF_MONTH, 23 );
		
		tarifaExcecao.validarDatas();
	}
	
	/**
	 * 
	 */
	@Test( expected= IllegalArgumentException.class )
	public void validarDatasMustFail()
	{
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		
		tarifaExcecao.setDataInicio( Calendar.getInstance() );
		tarifaExcecao.setDataFim( Calendar.getInstance() );
		
		tarifaExcecao.getDataInicio().set( Calendar.DAY_OF_MONTH, 29 );
		tarifaExcecao.getDataFim().set( Calendar.DAY_OF_MONTH, 23 );
		
		tarifaExcecao.validarDatas();
	}
	
	@Test
	public void validarTarifasDeExcecao()
	{
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		
		tarifaExcecao.setDataInicio( Calendar.getInstance() );
		tarifaExcecao.setDataFim( Calendar.getInstance() );
		
		tarifaExcecao.getDataInicio().set( Calendar.DAY_OF_MONTH, 20 );
		tarifaExcecao.getDataFim().set( Calendar.DAY_OF_MONTH, 23 );
		
		final double valorPadrao = 350.00;
		Set<Tarifa> tarifas = new HashSet<Tarifa>();
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime( tarifaExcecao.getDataInicio().getTime() );
		
        while (dataInicial.get(Calendar.DAY_OF_MONTH) <= tarifaExcecao.getDataFim().get(Calendar.DAY_OF_MONTH)){    
        	int diaIndex = dataInicial.get( Calendar.DAY_OF_WEEK );
        	Dia dia = Dia.getDiasOrdenados().get( diaIndex-1 );
        	
        	Tarifa tarifa = new Tarifa();
        	tarifa.setDia( dia );
        	tarifa.setPreco( valorPadrao );
       	
        	System.out.println( dia.toString() );
        	tarifas.add( tarifa );
        	
        	dataInicial.add(Calendar.DAY_OF_MONTH, 1);    
        } 
        
        tarifaExcecao.setTarifas(tarifas);
        tarifaExcecao.validarTarifas();
	}
	
	@Test
	public void validarTarifasDeTodosDiasMustPass()
	{
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		
		tarifaExcecao.setDataInicio( Calendar.getInstance() );
		tarifaExcecao.setDataFim( Calendar.getInstance() );
		
		tarifaExcecao.getDataInicio().set( Calendar.DAY_OF_MONTH, 20 );
		tarifaExcecao.getDataFim().set( Calendar.DAY_OF_MONTH, 27 );
		
		final double valorPadrao = 350.00;
		Set<Tarifa> tarifas = new HashSet<Tarifa>();
		
		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setPreco( valorPadrao );
			tarifa.setDia( dia );
			
			tarifas.add( tarifa );
		}
         
        
        tarifaExcecao.setTarifas(tarifas);
        tarifaExcecao.validarTarifas();
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void validarTarifasDeTodosDiasMustFail()
	{
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		
		tarifaExcecao.setDataInicio( Calendar.getInstance() );
		tarifaExcecao.setDataFim( Calendar.getInstance() );
		
		tarifaExcecao.getDataInicio().set( Calendar.DAY_OF_MONTH, 20 );
		tarifaExcecao.getDataFim().set( Calendar.DAY_OF_MONTH, 27 );
		
		final double valorPadrao = 350.00;
		Set<Tarifa> tarifas = new HashSet<Tarifa>();
		
		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setPreco( valorPadrao );
			tarifa.setDia( dia );
			
			tarifas.add( tarifa );
		}
		
		List<Tarifa> tarifasList = new ArrayList<Tarifa>( tarifas );
		tarifasList.get( 0 ).setDia( tarifasList.get( 2 ).getDia() );
		
		tarifas = new HashSet<Tarifa>( tarifasList );
		
        tarifaExcecao.setTarifas(tarifas);
        tarifaExcecao.validarTarifas();
        
        Assert.fail();
        
        
	}
}
