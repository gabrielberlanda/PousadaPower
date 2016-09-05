/**
 * 
 */
package com.digows.blank.test.domain.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;

import com.digows.blank.domain.entity.tipoquarto.TipoQuarto;
import com.digows.blank.domain.entity.tipoquarto.tarifa.Dia;
import com.digows.blank.domain.entity.tipoquarto.tarifa.Tarifa;
import com.digows.blank.domain.entity.tipoquarto.tarifa.TarifaExcecao;
import com.digows.blank.domain.service.TipoQuartoService;
import com.digows.blank.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author berlanda
 *
 */
public class TipoQuartoServiceIntegrationTests extends AbstractIntegrationTests
{

	/**
	 * 
	 */
	@Autowired
	private TipoQuartoService tipoQuartoService;
	
	/*
	 * Testes
	 */
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void findTipoQuartoByIdMustPass () {
		TipoQuarto tipoQuarto = this.tipoQuartoService.findTipoQuartoById( 1000L );
		
		Assert.assertNotNull( tipoQuarto );
		
		Assert.assertEquals( "Triplo" , tipoQuarto.getNome() );
		Assert.assertEquals( "Quarto triplo confortavel" , tipoQuarto.getObservacao() );
		Assert.assertEquals( new Integer(3) , tipoQuarto.getOcupacaoMaxima() );
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao() );
		Assert.assertTrue( tipoQuarto.getTarifasPadrao().size() == 7 );
	}
	
	@Test( expected= IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void findTipoQuartoByIdMustFailWithWrongId () {
		TipoQuarto tipoQuarto = this.tipoQuartoService.findTipoQuartoById( 9874L );
		Assert.fail();
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void listTipoQuartosByFiltersMustReturn3TipoQuarto () {
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( null, null );
		
		Assert.assertNotNull( tipoQuartos );
		Assert.assertTrue( tipoQuartos.getContent().size() == 3 );
		
		Assert.assertTrue( tipoQuartos.getContent().get( 0 ).getTarifasPadrao().size() == 7 );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void listTipoQuartosByFiltersMustReturn2TipoQuarto () {
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( "Duplo", null );
		
		Assert.assertNotNull( tipoQuartos );
		
		Assert.assertTrue( tipoQuartos.getContent().size() == 2 );
		
		Assert.assertTrue( tipoQuartos.getContent().get( 0 ).getTarifasPadrao().size() == 7 );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void listTipoQuartosByFiltersMustReturn1TipoQuarto () {
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( "Triplo", null );
		
		Assert.assertNotNull( tipoQuartos );
		
		Assert.assertTrue( tipoQuartos.getContent().size() == 1 );
		
		Assert.assertTrue( tipoQuartos.getContent().get( 0 ).getTarifasPadrao().size() == 7 );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void listTipoQuartosByFiltersMustNotReturnTipoQuarto () {
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( "Não deve retornar registros", null );
		
		Assert.assertNotNull( tipoQuartos );
		
		Assert.assertTrue( tipoQuartos.getContent().size() == 0 );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void removeTipoQuartoMustPass()
	{
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( null, null );
		
		Assert.assertTrue( tipoQuartos.getContent().size() == 3 );
		
		this.tipoQuartoService.removeTipoQuarto( 1000L );
		
		tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( null, null );
		
		Assert.assertTrue( tipoQuartos.getContent().size() == 2 );
	}
	
	@Test( expected = EmptyResultDataAccessException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void removeTipoQuartoMustFail()
	{
		this.tipoQuartoService.removeTipoQuarto( 9988L );
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTipoQuartoMustPass()
	{
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( null, null );
		Assert.assertTrue( tipoQuartos.getContent().size() == 3 );
		
		TipoQuarto tipoQuarto = new TipoQuarto();
		
		tipoQuarto.setNome( "Tripo super luxo" );
		tipoQuarto.setOcupacaoMaxima( 3 );
		tipoQuarto.setTarifasPadrao( new HashSet<Tarifa>() );
		
		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setDia( dia );
			tarifa.setPreco( new Double( 300 ) );
			
			tipoQuarto.getTarifasPadrao().add( tarifa );
		}

		Assert.assertTrue( tipoQuarto.getTarifasPadrao().size() == 7 );
		
		tipoQuarto = this.tipoQuartoService.insertTipoQuarto( tipoQuarto );
		
		Assert.assertNotNull( tipoQuarto );
		Assert.assertNotNull( tipoQuarto.getId() );
		
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao() );
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao().iterator().next().getId() );
	}
	
	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTipoQuartoMustFailWithout7TarifasPadrao()
	{
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( null, null );
		Assert.assertTrue( tipoQuartos.getContent().size() == 3 );
		
		TipoQuarto tipoQuarto = new TipoQuarto();
		
		tipoQuarto.setNome( "Tripo super luxo" );
		tipoQuarto.setOcupacaoMaxima( 3 );
		tipoQuarto.setTarifasPadrao( new HashSet<Tarifa>() );
		
		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setDia( dia );
			tarifa.setPreco( new Double( 300 ) );
			
			tipoQuarto.getTarifasPadrao().add( tarifa );
		}
		
		
		tipoQuarto.getTarifasPadrao().remove( tipoQuarto.getTarifasPadrao().iterator().next() );
		
		Assert.assertTrue( tipoQuarto.getTarifasPadrao().size() == 6 );
		
		tipoQuarto = this.tipoQuartoService.insertTipoQuarto( tipoQuarto );
		Assert.fail();
	}
	
	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTipoQuartoMustFailWith8TarifaPadrao()
	{
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( null, null );
		Assert.assertTrue( tipoQuartos.getContent().size() == 3 );
		
		TipoQuarto tipoQuarto = new TipoQuarto();
		
		tipoQuarto.setNome( "Tripo super luxo" );
		tipoQuarto.setOcupacaoMaxima( 3 );
		tipoQuarto.setTarifasPadrao( new HashSet<Tarifa>() );
		
		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setDia( dia );
			tarifa.setPreco( new Double( 300 ) );
			
			tipoQuarto.getTarifasPadrao().add( tarifa );
		}

		Tarifa oitavaTarifa = new Tarifa();
		oitavaTarifa.setDia( Dia.DOMINGO );
		oitavaTarifa.setPreco( new Double(380) );
		
		tipoQuarto.getTarifasPadrao().add( oitavaTarifa );
		
		
		Assert.assertTrue( tipoQuarto.getTarifasPadrao().size() == 8 );
		
		tipoQuarto = this.tipoQuartoService.insertTipoQuarto( tipoQuarto );
		
		Assert.assertNotNull( tipoQuarto );
		Assert.assertNotNull( tipoQuarto.getId() );
		
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao() );
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao().iterator().next().getId() );
		Assert.fail();
	}
	
	@Test( expected = NullPointerException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTipoQuartoMustFailWithoutTarifaDia()
	{
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( null, null );
		Assert.assertTrue( tipoQuartos.getContent().size() == 3 );
		
		TipoQuarto tipoQuarto = new TipoQuarto();
		
		tipoQuarto.setNome( "Tripo super luxo" );
		tipoQuarto.setOcupacaoMaxima( 3 );
		tipoQuarto.setTarifasPadrao( new HashSet<Tarifa>() );
		
		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setDia( dia );
			tarifa.setPreco( new Double( 300 ) );
			
			tipoQuarto.getTarifasPadrao().add( tarifa );
		}

		tipoQuarto.getTarifasPadrao().iterator().next().setDia( null );
		Assert.assertTrue( tipoQuarto.getTarifasPadrao().size() == 7 );
		
		tipoQuarto = this.tipoQuartoService.insertTipoQuarto( tipoQuarto );
		
		Assert.assertNotNull( tipoQuarto );
		Assert.assertNotNull( tipoQuarto.getId() );
		
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao() );
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao().iterator().next().getId() );
		Assert.fail();
	}
	
	@Test( expected = ValidationException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTipoQuartoMustFailWithoutTarifaPreco()
	{
		Page<TipoQuarto> tipoQuartos = this.tipoQuartoService.listTipoQuartosByFilters( null, null );
		Assert.assertTrue( tipoQuartos.getContent().size() == 3 );
		
		TipoQuarto tipoQuarto = new TipoQuarto();
		
		tipoQuarto.setNome( "Tripo super luxo" );
		tipoQuarto.setOcupacaoMaxima( 3 );
		tipoQuarto.setTarifasPadrao( new HashSet<Tarifa>() );
		
		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setDia( dia );
			tarifa.setPreco( null );
			
			tipoQuarto.getTarifasPadrao().add( tarifa );
		}

		Assert.assertTrue( tipoQuarto.getTarifasPadrao().size() == 7 );
		
		tipoQuarto = this.tipoQuartoService.insertTipoQuarto( tipoQuarto );
		
		Assert.assertNotNull( tipoQuarto );
		Assert.assertNotNull( tipoQuarto.getId() );
		
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao() );
		Assert.assertNotNull( tipoQuarto.getTarifasPadrao().iterator().next().getId() );
		
		Assert.fail();
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void updateTipoQuartoMustPass()
	{
		TipoQuarto tipoQuarto = this.tipoQuartoService.findTipoQuartoById( 1000L );
		
		Assert.assertNotNull( tipoQuarto );
		
		tipoQuarto.setNome( "Tipo de quarto alterado." );
		tipoQuarto.setOcupacaoMaxima( 5 );
		//FIXME  Não está atualizando a tarifa.
		tipoQuarto.getTarifasPadrao().iterator().next().setPreco( new Double (450) );
		Dia dia = tipoQuarto.getTarifasPadrao().iterator().next().getDia();
		
		this.tipoQuartoService.updateTipoQuarto( tipoQuarto );
		
		tipoQuarto = this.tipoQuartoService.findTipoQuartoById( 1000L );
		
		Assert.assertEquals( "Tipo de quarto alterado.", tipoQuarto.getNome() );
		Assert.assertTrue( tipoQuarto.getOcupacaoMaxima() == 5 );
		
		for ( Tarifa tarifa : tipoQuarto.getTarifasPadrao() )
		{
			if ( tarifa.getDia().equals( dia ) )
			{
				Assert.assertEquals( new Double (450), tarifa.getPreco() );
			}
		}
		
	}
	
	@Test( expected = DataIntegrityViolationException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void updateTipoQuartoMustFailWithUniqueViolation()
	{
		TipoQuarto tipoQuarto = this.tipoQuartoService.findTipoQuartoById( 1000L );
		
		Assert.assertNotNull( tipoQuarto );
		Assert.assertNotEquals( "Duplo", tipoQuarto.getNome() );
		
		tipoQuarto.setNome( "Duplo" );
		
		this.tipoQuartoService.updateTipoQuarto( tipoQuarto );
		
		Assert.fail();
	}
	
	@Test( expected = NullPointerException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void updateTipoQuartoMustFailWithWrongTarifa()
	{
		TipoQuarto tipoQuarto = this.tipoQuartoService.findTipoQuartoById( 1000L );
		
		Assert.assertNotNull( tipoQuarto );
		
		tipoQuarto.setNome( "Tipo de quarto alterado." );
		tipoQuarto.setOcupacaoMaxima( 5 );
		
		tipoQuarto.getTarifasPadrao().iterator().next().setPreco( null );
		tipoQuarto.getTarifasPadrao().iterator().next().setDia( null );
		
		this.tipoQuartoService.updateTipoQuarto( tipoQuarto );
		
		Assert.fail();
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void listTarifaExcecoesByFiltersAndTipoQuartoIdMustReturn2TarifaExcecao()
	{
		Page<TarifaExcecao> tarifasExcecao = this.tipoQuartoService.listTarifaExcecoesByFiltersAndTipoQuartoId( null, null, null, 1001L, null );
		
		Assert.assertNotNull( tarifasExcecao );
		Assert.assertTrue( tarifasExcecao.getContent().size() == 2 );
		Assert.assertNotNull( tarifasExcecao.getContent().get( 0 ).getTarifas() );
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void listTarifaExcecoesByFiltersAndTipoQuartoIdMustReturn1TarifaExcecao()
	{
		Page<TarifaExcecao> tarifasExcecao = this.tipoQuartoService.listTarifaExcecoesByFiltersAndTipoQuartoId( null, null, null, 1000L, null );
		
		Assert.assertNotNull( tarifasExcecao );
		Assert.assertTrue( tarifasExcecao.getContent().size() == 1 );
		Assert.assertNotNull( tarifasExcecao.getContent().get( 0 ).getTarifas() );
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void listTarifaExcecoesByFiltersAndTipoQuartoIdMustNotReturnTarifaExcecao()
	{
		Page<TarifaExcecao> tarifasExcecao = this.tipoQuartoService.listTarifaExcecoesByFiltersAndTipoQuartoId( null, null, null, 1002L, null );
		
		Assert.assertNotNull( tarifasExcecao );
		Assert.assertTrue( tarifasExcecao.getContent().size() == 0 );
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void listTarifaExcecoesByFiltersAndTipoQuartoIdMustReturn1TarifaExcecaoWithDateFilters()
	{
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set( Calendar.DAY_OF_MONTH, 30 );
		dataInicio.set( Calendar.MONTH, 4 );
		dataInicio.set( Calendar.YEAR, 2016 );
		
			
		Calendar dataFim = Calendar.getInstance();
		dataFim.set( Calendar.DAY_OF_MONTH, 21 );
		dataFim.set( Calendar.MONTH, 5 );
		dataFim.set( Calendar.YEAR, 2016 );
		
		
		Page<TarifaExcecao> tarifasExcecao = this.tipoQuartoService.listTarifaExcecoesByFiltersAndTipoQuartoId( null, dataInicio, dataFim, 1001L, null );
		
		Assert.assertNotNull( tarifasExcecao );
		Assert.assertTrue( tarifasExcecao.getContent().size() == 1 );
		Assert.assertNotNull( tarifasExcecao.getContent().get( 0 ).getTarifas() );
		Assert.assertTrue( tarifasExcecao.getContent().get( 0 ).getTarifas().size() == 7 );
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTarifaExcecaoMustPass()
	{
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set( Calendar.DAY_OF_MONTH, 2 );
		dataInicio.set( Calendar.MONTH, 8 );
		dataInicio.set( Calendar.YEAR, 2016 );
		
		Calendar dataFim = Calendar.getInstance();
		dataFim.set( Calendar.DAY_OF_MONTH, 3 );
		dataFim.set( Calendar.MONTH, 8 );
		dataFim.set( Calendar.YEAR, 2016 );
		
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		tarifaExcecao.setNome( "Tarifa com 2 dias");
		tarifaExcecao.setDataInicio( dataInicio );
		tarifaExcecao.setDataFim( dataFim );
		tarifaExcecao.setTipoQuarto( new TipoQuarto( 1000L ) );
		
		Tarifa tarifaSexta = new Tarifa();
		tarifaSexta.setDia( Dia.SEXTA );
		tarifaSexta.setPreco( new Double(450) );
		
		Tarifa tarifaSabado = new Tarifa();
		tarifaSabado.setDia( Dia.SABADO );
		tarifaSabado.setPreco( new Double(500) );
		
		tarifaExcecao.setTarifas( new HashSet<Tarifa>() );
		tarifaExcecao.getTarifas().add( tarifaSexta );
		tarifaExcecao.getTarifas().add( tarifaSabado );
		
		tarifaExcecao = this.tipoQuartoService.insertTarifaExcecao( tarifaExcecao );
		
		Assert.assertNotNull( tarifaExcecao );
		Assert.assertNotNull( tarifaExcecao.getId() );
		
	}
	
	@Test( expected = IllegalArgumentException.class)
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTarifaExcecaoMustFailWithWrongTarifaDay()
	{
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set( Calendar.DAY_OF_MONTH, 2 );
		dataInicio.set( Calendar.MONTH, 8 );
		dataInicio.set( Calendar.YEAR, 2016 );
		
		Calendar dataFim = Calendar.getInstance();
		dataFim.set( Calendar.DAY_OF_MONTH, 3 );
		dataFim.set( Calendar.MONTH, 8 );
		dataFim.set( Calendar.YEAR, 2016 );
		
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		tarifaExcecao.setNome( "Tarifa com 2 dias");
		tarifaExcecao.setDataInicio( dataInicio );
		tarifaExcecao.setDataFim( dataFim );
		tarifaExcecao.setTipoQuarto( new TipoQuarto( 1000L ) );
		
		Tarifa tarifaSexta = new Tarifa();
		tarifaSexta.setDia( Dia.SEXTA );
		tarifaSexta.setPreco( new Double(450) );
		
		Tarifa tarifaSabado = new Tarifa();
		tarifaSabado.setDia( Dia.SEGUNDA );
		tarifaSabado.setPreco( new Double(500) );
		
		tarifaExcecao.setTarifas( new HashSet<Tarifa>() );
		tarifaExcecao.getTarifas().add( tarifaSexta );
		tarifaExcecao.getTarifas().add( tarifaSabado );
		
		tarifaExcecao = this.tipoQuartoService.insertTarifaExcecao( tarifaExcecao );
		
		Assert.assertNotNull( tarifaExcecao );
		Assert.assertNotNull( tarifaExcecao.getId() );
		
		Assert.fail();
	}

	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTarifaExcecaoMustPassWithMoreThanAWeek()
	{
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set( Calendar.DAY_OF_MONTH, 2 );
		dataInicio.set( Calendar.MONTH, 8 );
		dataInicio.set( Calendar.YEAR, 2016 );
		
		Calendar dataFim = Calendar.getInstance();
		dataFim.set( Calendar.DAY_OF_MONTH, 8 );
		dataFim.set( Calendar.MONTH, 8 );
		dataFim.set( Calendar.YEAR, 2016 );
		
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		tarifaExcecao.setNome( "Tarifa com 2 dias");
		tarifaExcecao.setDataInicio( dataInicio );
		tarifaExcecao.setDataFim( dataFim );
		tarifaExcecao.setTipoQuarto( new TipoQuarto( 1000L ) );

		tarifaExcecao.setTarifas( new HashSet<Tarifa>() );

		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setDia( dia );
			tarifa.setPreco( new Double( 300 ) );
			
			tarifaExcecao.getTarifas().add( tarifa );
		}
		
		tarifaExcecao = this.tipoQuartoService.insertTarifaExcecao( tarifaExcecao );
		
		Assert.assertNotNull( tarifaExcecao );
		Assert.assertNotNull( tarifaExcecao.getId() );
	}

	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void insertTarifaExcecaoMustFailWithMoreThanAWeekAnd6Tarifa()
	{
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set( Calendar.DAY_OF_MONTH, 2 );
		dataInicio.set( Calendar.MONTH, 8 );
		dataInicio.set( Calendar.YEAR, 2016 );
		
		Calendar dataFim = Calendar.getInstance();
		dataFim.set( Calendar.DAY_OF_MONTH, 8 );
		dataFim.set( Calendar.MONTH, 8 );
		dataFim.set( Calendar.YEAR, 2016 );
		
		TarifaExcecao tarifaExcecao = new TarifaExcecao();
		tarifaExcecao.setNome( "Tarifa com 2 dias");
		tarifaExcecao.setDataInicio( dataInicio );
		tarifaExcecao.setDataFim( dataFim );
		tarifaExcecao.setTipoQuarto( new TipoQuarto( 1000L ) );

		tarifaExcecao.setTarifas( new HashSet<Tarifa>() );

		for ( Dia dia : Dia.getDiasOrdenados() )
		{
			Tarifa tarifa = new Tarifa();
			tarifa.setDia( dia );
			tarifa.setPreco( new Double( 300 ) );
			
			tarifaExcecao.getTarifas().add( tarifa );
		}
		
		tarifaExcecao.getTarifas().remove( tarifaExcecao.getTarifas().iterator().next() );
		tarifaExcecao = this.tipoQuartoService.insertTarifaExcecao( tarifaExcecao );
		
		Assert.assertNotNull( tarifaExcecao );
		Assert.assertNotNull( tarifaExcecao.getId() );
		
		Assert.fail();
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void findTarifaExcecaoByIdMustPass()
	{
		TarifaExcecao tarifaExcecao = this.tipoQuartoService.findTarifaExcecaoById( 1000L );

		Calendar dataInicio = new GregorianCalendar();
		Calendar dataFim = new GregorianCalendar();	
		
		dataInicio.set( 2016, 11, 24, 00, 00, 00 );
		dataFim.set( 2016, 11, 26, 00, 00, 00 );

		Assert.assertEquals( "Tarifa de Natal 2016 - Triplo", tarifaExcecao.getNome() );
		
		Assert.assertEquals( dataInicio.get( Calendar.YEAR ), tarifaExcecao.getDataInicio().get( Calendar.YEAR ) );
		Assert.assertEquals( dataInicio.get( Calendar.MONTH ), tarifaExcecao.getDataInicio().get( Calendar.MONTH ) );
		Assert.assertEquals( dataInicio.get( Calendar.DAY_OF_MONTH ), tarifaExcecao.getDataInicio().get( Calendar.DAY_OF_MONTH ) );
		
		Assert.assertEquals( dataFim.get( Calendar.YEAR ), tarifaExcecao.getDataFim().get( Calendar.YEAR ) );
		Assert.assertEquals( dataFim.get( Calendar.MONTH ), tarifaExcecao.getDataFim().get( Calendar.MONTH ) );
		Assert.assertEquals( dataFim.get( Calendar.DAY_OF_MONTH ), tarifaExcecao.getDataFim().get( Calendar.DAY_OF_MONTH ) );
		
		Assert.assertNotNull( tarifaExcecao.getTarifas() );
		System.out.println( tarifaExcecao.getTarifas().size() );
		
	}

	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void findTarifaExcecaoByIdMustFailWithInvalidId()
	{
		TarifaExcecao tarifaExcecao = this.tipoQuartoService.findTarifaExcecaoById( 1895L );
		
		Assert.fail();
	}
	
	@Test()
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void updateTarifaExcecaoMustPass()
	{
		TarifaExcecao tarifaExcecao = this.tipoQuartoService.findTarifaExcecaoById( 1000L );
		
		Assert.assertNotNull( tarifaExcecao );
		Assert.assertNotNull( tarifaExcecao.getTarifas() );
		
		Assert.assertEquals( "Tarifa de Natal 2016 - Triplo", tarifaExcecao.getNome() );
		Assert.assertEquals( tarifaExcecao.getDataInicio().get( Calendar.DAY_OF_MONTH ), 24 );
		Assert.assertEquals( tarifaExcecao.getDataInicio().get( Calendar.MONTH ), 11 );
		
		Assert.assertEquals( tarifaExcecao.getDataFim().get( Calendar.DAY_OF_MONTH ), 26 );
		Assert.assertEquals( tarifaExcecao.getDataFim().get( Calendar.MONTH ), 11 );
		
		Assert.assertTrue( tarifaExcecao.getTarifas().size() == 3 ); 
		
		//Dia 26 é uma segunda feira, logo se eu inserir mais um dia no final será necessário uma tarifa para terça
		
		tarifaExcecao.getDataFim().set( Calendar.DAY_OF_MONTH, 27 );
		tarifaExcecao.setNome( "Tarifa de natal atualizada!" );

		Tarifa tarifaDeTerca = new Tarifa();
		tarifaDeTerca.setDia( Dia.TERCA );
		tarifaDeTerca.setPreco( new Double ( 340) );
		
		tarifaExcecao.getTarifas().add( tarifaDeTerca );
		
		this.tipoQuartoService.updateTarifaExcecao( tarifaExcecao );
		
		tarifaExcecao = this.tipoQuartoService.findTarifaExcecaoById( 1000L );
		
		Assert.assertEquals( "Tarifa de natal atualizada!", tarifaExcecao.getNome() );
		Assert.assertEquals( 27, tarifaExcecao.getDataFim().get( Calendar.DAY_OF_MONTH ) );
		Assert.assertTrue( tarifaExcecao.getTarifas().size() == 4 );
	}

	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void updateTarifaExcecaoMustFailAddingADayAndDontAddTarifa()
	{
		TarifaExcecao tarifaExcecao = this.tipoQuartoService.findTarifaExcecaoById( 1000L );
		
		Assert.assertNotNull( tarifaExcecao );
		Assert.assertNotNull( tarifaExcecao.getTarifas() );
		
		Assert.assertEquals( "Tarifa de Natal 2016 - Triplo", tarifaExcecao.getNome() );
		Assert.assertEquals( tarifaExcecao.getDataInicio().get( Calendar.DAY_OF_MONTH ), 24 );
		Assert.assertEquals( tarifaExcecao.getDataInicio().get( Calendar.MONTH ), 11 );
		
		Assert.assertEquals( tarifaExcecao.getDataFim().get( Calendar.DAY_OF_MONTH ), 26 );
		Assert.assertEquals( tarifaExcecao.getDataFim().get( Calendar.MONTH ), 11 );
		
		Assert.assertTrue( tarifaExcecao.getTarifas().size() == 3 ); 
		
		//Dia 26 é uma segunda feira, logo se eu inserir mais um dia no final será necessário uma tarifa para terça
		
		tarifaExcecao.getDataFim().set( Calendar.DAY_OF_MONTH, 27 );
		tarifaExcecao.setNome( "Tarifa de natal atualizada!" );
		
		this.tipoQuartoService.updateTarifaExcecao( tarifaExcecao );
		
		Assert.fail();
	}
	
	@Test( expected= IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/tipoquarto/TipoQuartoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaExcecaoDataSet.xml",
		"/dataSet/tipoquarto/tarifa/TarifaDataSet.xml",
	})
	public void updateTarifaExcecaoMustFailWithWrongDay()
	{
		TarifaExcecao tarifaExcecao = this.tipoQuartoService.findTarifaExcecaoById( 1000L );
		
		Assert.assertNotNull( tarifaExcecao );
		Assert.assertNotNull( tarifaExcecao.getTarifas() );
		
		Assert.assertEquals( "Tarifa de Natal 2016 - Triplo", tarifaExcecao.getNome() );
		Assert.assertEquals( tarifaExcecao.getDataInicio().get( Calendar.DAY_OF_MONTH ), 24 );
		Assert.assertEquals( tarifaExcecao.getDataInicio().get( Calendar.MONTH ), 11 );
		
		Assert.assertEquals( tarifaExcecao.getDataFim().get( Calendar.DAY_OF_MONTH ), 26 );
		Assert.assertEquals( tarifaExcecao.getDataFim().get( Calendar.MONTH ), 11 );
		
		Assert.assertTrue( tarifaExcecao.getTarifas().size() == 3 ); 
		
		//Dia 26 é uma segunda feira, logo se eu inserir mais um dia no final será necessário uma tarifa para terça
		
		tarifaExcecao.getDataFim().set( Calendar.DAY_OF_MONTH, 27 );
		tarifaExcecao.setNome( "Tarifa de natal atualizada!" );

		Tarifa tarifaDeTerca = new Tarifa();
		tarifaDeTerca.setDia( Dia.QUARTA );
		tarifaDeTerca.setPreco( new Double ( 340) );
		
		tarifaExcecao.getTarifas().add( tarifaDeTerca );
		
		this.tipoQuartoService.updateTarifaExcecao( tarifaExcecao );
		Assert.fail();
	}


}
