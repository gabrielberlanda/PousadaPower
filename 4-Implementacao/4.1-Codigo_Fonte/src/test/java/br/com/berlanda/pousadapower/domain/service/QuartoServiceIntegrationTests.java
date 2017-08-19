/**
 * 
 */
package br.com.berlanda.pousadapower.domain.service;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.com.berlanda.pousadapower.domain.AbstractIntegrationTests;
import br.com.berlanda.pousadapower.domain.entity.quarto.Quarto;
import br.com.berlanda.pousadapower.domain.entity.quarto.StatusQuarto;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.TipoQuarto;

/**
 * @author berlanda
 *
 */
public class QuartoServiceIntegrationTests extends AbstractIntegrationTests
{

	@Autowired
	private QuartoService quartoService;
	
	//METODOS
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void insertQuartoMustPass()
	{
		Page<Quarto> quartos = this.quartoService.listQuartosByFilters( null, null );
		
		Assert.assertEquals( 5, quartos.getTotalElements() );
		
		Quarto quarto = new Quarto();
		
		quarto.setAtivo( true );
		quarto.setNome( "Quarto novo" );
		quarto.setObservacao( "Observação" );
		quarto.setStatus( StatusQuarto.LIMPO );
		quarto.setTipoQuarto( new TipoQuarto(1000L) );

		quarto = this.quartoService.insertQuarto( quarto, null );
		
		quartos = this.quartoService.listQuartosByFilters( null, null );
		
		Assert.assertEquals( 6, quartos.getTotalElements() );
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void insertQuartoMustFail()
	{
		Quarto quarto = new Quarto();
		
		quarto.setAtivo( true );
		quarto.setNome( null );
		quarto.setObservacao( "Observação" );
		quarto.setStatus( StatusQuarto.LIMPO );
		quarto.setTipoQuarto( null );

		quarto = this.quartoService.insertQuarto( quarto, null );
		
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void updateQuartoMustPass()
	{
		Quarto quarto = this.quartoService.findQuartoById( 1000L );
		
		Assert.assertEquals( "101A", quarto.getNome() );
		Assert.assertEquals( StatusQuarto.LIMPO, quarto.getStatus() );
		Assert.assertEquals( new Long( 1000L ), quarto.getTipoQuarto().getId() );

		quarto.setNome( "quarto alterado" );
		quarto.setTipoQuarto( new TipoQuarto( 1001L ) );
		quarto.setStatus( StatusQuarto.SUJO );
		
		this.quartoService.updateQuarto( quarto, null );
		
		quarto = this.quartoService.findQuartoById( 1000L );
		Assert.assertEquals( "quarto alterado", quarto.getNome() );
		Assert.assertEquals( new Long(1001L), quarto.getTipoQuarto().getId() );
		Assert.assertEquals( StatusQuarto.SUJO, quarto.getStatus() );
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void updateQuartoMustFail()
	{
		Quarto quarto = this.quartoService.findQuartoById( 1000L );
		
		quarto.setNome( "quarto alterado" );
		quarto.setTipoQuarto( null );
		quarto.setStatus( StatusQuarto.SUJO );
		
		this.quartoService.updateQuarto( quarto, null );
		
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void findQuartoByIdMustPass()
	{
		Quarto quarto = this.quartoService.findQuartoById( 1000L );
		
		Assert.assertEquals( "101A", quarto.getNome() );
		Assert.assertEquals( StatusQuarto.LIMPO, quarto.getStatus() );
		Assert.assertEquals( new Long( 1000L ), quarto.getTipoQuarto().getId() );
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void findQuartoByIdMustFail()
	{
		Quarto quarto = this.quartoService.findQuartoById( 4444L );
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void listQuartosByFiltersMustReturn5Quartos()
	{
		Page<Quarto> quartos = this.quartoService.listQuartosByFilters( null, null );
		
		Assert.assertEquals( 5, quartos.getTotalElements() );
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void listQuartosByFiltersMustReturn1Quarto()
	{
		Page<Quarto> quartos = this.quartoService.listQuartosByFilters( "500", null );
		
		Assert.assertEquals( 1, quartos.getTotalElements() );
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void listQuartosByFiltersMustNotReturnQuarto()
	{
		Page<Quarto> quartos = this.quartoService.listQuartosByFilters( "Não deve retornar quarto", null );
		
		Assert.assertEquals( 0, quartos.getTotalElements() );
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void deleteQuartoMustPass()
	{
		Page<Quarto> quartos = this.quartoService.listQuartosByFilters( null, null );
		Assert.assertEquals( 5, quartos.getTotalElements() );
		
		this.quartoService.removeQuarto( 1000L );
		
		quartos = this.quartoService.listQuartosByFilters( null, null );
		Assert.assertEquals( 4, quartos.getTotalElements() );
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
		"/dataset/quarto/QuartoDataSet.xml",
	})
	public void deleteQuartoMustFailWithInvalidId()
	{
		this.quartoService.removeQuarto( 5555L );
	}
}
