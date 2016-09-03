/**
 * 
 */
package com.digows.blank.test.domain.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.digows.blank.domain.entity.tipoquarto.tarifa.Dia;
import com.digows.blank.domain.entity.tipoquarto.tarifa.Tarifa;
import com.digows.blank.domain.entity.tipoquarto.tarifa.TarifaExcecao;
import com.digows.blank.test.domain.AbstractUnitTests;

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
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		
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
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		
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
		List<Tarifa> tarifas = new ArrayList<Tarifa>();
		
		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setPreco( valorPadrao );
			tarifa.setDia( dia );
			
			tarifas.add( tarifa );
		}
         
        tarifas.get( 0 ).setDia( tarifas.get( 1 ).getDia() ); //Estou invertendo uma tarifa para o teste falhar.
        
        tarifaExcecao.setTarifas(tarifas);
        tarifaExcecao.validarTarifas();
        
        Assert.fail();
        
        
	}
}
