/**
 * 
 */
package com.digows.blank.test.domain.service;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;

import com.digows.blank.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.com.berlanda.pousadapower.domain.entity.fornecedor.Fornecedor;
import br.com.berlanda.pousadapower.domain.entity.pessoa.Hospede;
import br.com.berlanda.pousadapower.domain.service.FornecedorService;

/**
 * @author Berlanda
 *
 */
public class FornecedorServiceIntegrationTests extends AbstractIntegrationTests
{

	@Autowired
	private FornecedorService fornecedorService;
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
	})
	public void insertFornecedorMustPass()
	{
		Page<Fornecedor> fornecedores = this.fornecedorService.listFornecedoresByFilters( null, null );
		Assert.assertTrue( fornecedores.getTotalElements() == 3 );
		
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setNomeFantasia( "Fornecedor teste" );
		fornecedor.setRazaoSocial( "Razao Social" );
		fornecedor.setTelefone( "999999999" );
		fornecedor.setEmail( "fornecedor@teste.com" );
		fornecedor.setCnpj( "1241241241124" );
		
		fornecedor = this.fornecedorService.insertFornecedor( fornecedor );

		Assert.assertNotNull( fornecedor );
		Assert.assertNotNull( fornecedor.getId() );
		Assert.assertEquals( fornecedor.getRazaoSocial(), "Razao Social" );
		Assert.assertEquals( fornecedor.getNomeFantasia(), "Fornecedor teste" );
		Assert.assertEquals( fornecedor.getTelefone(), "999999999" );
		Assert.assertEquals( fornecedor.getEmail(), "fornecedor@teste.com" );
		
		
	}
	
	@Test( expected = ValidationException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
	})
	public void insertFornecedorMustFail()
	{
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setRazaoSocial( null );
		fornecedor.setNomeFantasia( null );
		fornecedor.setTelefone( null );
		fornecedor.setEmail( "fornecedor@teste.com" );
		
		fornecedor = this.fornecedorService.insertFornecedor( fornecedor );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
	})
	public void updateHospedeMustPass()
	{
		Fornecedor fornecedor = this.fornecedorService.findFornecedorById( 1000L );
		
		Assert.assertEquals( "Fornecedor de bebida", fornecedor.getRazaoSocial() );
		Assert.assertEquals( "Fornecedor de bebida", fornecedor.getNomeFantasia() );
		Assert.assertEquals( "fornecedor@bebida.com", fornecedor.getEmail() );
		
		fornecedor.setRazaoSocial( "Fornecedor editado" );
		fornecedor.setNomeFantasia( "Fornecedor editado ltda" );
		fornecedor.setEmail( "fornecedor@pousadapower.com.br" );
		
		fornecedor = this.fornecedorService.updateFornecedor( fornecedor );
		
		Assert.assertTrue( fornecedor.getId() == 1000L );
		
		fornecedor = this.fornecedorService.findFornecedorById( 1000L );
		Assert.assertEquals( fornecedor.getRazaoSocial(), "Fornecedor editado" );
		Assert.assertEquals( fornecedor.getNomeFantasia(), "Fornecedor editado ltda" );
		Assert.assertEquals( fornecedor.getEmail(), "fornecedor@pousadapower.com.br" );
	}

	@Test( expected = DataIntegrityViolationException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
	})
	public void updateHospedeMustFail()
	{
		Fornecedor fornecedor = this.fornecedorService.findFornecedorById( 1000L );
		
		Assert.assertEquals( "Fornecedor de bebida", fornecedor.getRazaoSocial() );
		Assert.assertEquals( "Fornecedor de bebida", fornecedor.getNomeFantasia() );
		Assert.assertEquals( "fornecedor@bebida.com", fornecedor.getEmail() );
		
		fornecedor = this.fornecedorService.findFornecedorById( 1001L );
		
		fornecedor.setEmail( "fornecedor@bebida.com" );
		
		fornecedor = this.fornecedorService.updateFornecedor( fornecedor );
	}

//	@Test
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/fornecedor/FornecedorDataSet.xml",
//	})
//	public void listHospedesByFiltersMustReturnAllHospedes()
//	{
//		Page<Hospede> hospedes = this.pessoaService.listHospedesByFilters( null, null );
//		Assert.assertTrue( hospedes.getTotalElements() == 3 );
//	}
////	
//	@Test
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/fornecedor/FornecedorDataSet.xml",
//	})
//	public void listHospedesByFiltersMustReturn1Hospede()
//	{
//		Page<Hospede> hospedes = this.pessoaService.listHospedesByFilters( "hospede1@mail.com", null );
//		Assert.assertTrue( hospedes.getTotalElements() == 1 );
//		
//		hospedes = this.pessoaService.listHospedesByFilters( "aaa0000", null );
//		Assert.assertTrue( hospedes.getTotalElements() == 1 );
//	}
//	
//	@Test
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/fornecedor/FornecedorDataSet.xml",
//	})
//	public void listHospedesByFiltersMustReturn0Hospede()
//	{
//		Page<Hospede> hospedes = this.pessoaService.listHospedesByFilters( "Não deve retornar registros", null );
//		Assert.assertTrue( hospedes.getTotalElements() == 0 );
//	}
//	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
	})
	public void removeHospedeMustPass()
	{
		Page<Fornecedor> fornecedores = this.fornecedorService.listFornecedoresByFilters( null, null );
		Assert.assertTrue( fornecedores.getTotalElements() == 3 );
		
		this.fornecedorService.removeFornecedor( 1000L );
		
		fornecedores = this.fornecedorService.listFornecedoresByFilters( null, null );
		Assert.assertTrue( fornecedores.getTotalElements() == 2 );
		
	}
	
	@Test( expected = EmptyResultDataAccessException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
	})
	public void removeFornecedorMustFail()
	{
		this.fornecedorService.removeFornecedor( 9999L );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
	})
	public void findHospedeByIdMustPass()
	{
		Fornecedor fornecedor= this.fornecedorService.findFornecedorById( 1000L );
		
		Assert.assertNotNull( fornecedor );
		Assert.assertNotNull(  fornecedor.getId() );
		
		Assert.assertTrue( fornecedor.getId() == 1000L );
	}
	
	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
	})
	public void findHospedeByIdMustFail()
	{
		Fornecedor fornecedor = this.fornecedorService.findFornecedorById( 7896L );
				
	}
	
}
