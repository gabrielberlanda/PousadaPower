/**
 * 
 */
package com.digows.blank.test.domain.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;

import com.digows.blank.domain.entity.fornecedor.Fornecedor;
import com.digows.blank.domain.entity.pessoa.Hospede;
import com.digows.blank.domain.service.FornecedorService;
import com.digows.blank.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

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
	
//	@Test( expected = IllegalArgumentException.class )
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void insertHospedeMustFail()
//	{
//		Hospede hospede = new Hospede();
//		
//		hospede.setNome( null );
//		hospede.setTelefone( null );
//		hospede.setEmail( "hospede@teste.com" );
//		hospede.setCpf( "08789887930" );
//		hospede.setPlaca( "CBA0909" );
//		hospede.setModeloVeiculo( "Legacy verde" );
//		
//		hospede = this.pessoaService.insertHospede( hospede );
//	}
//	
//	@Test
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void updateHospedeMustPass()
//	{
//		Hospede hospede = this.pessoaService.findHospedeById( 1000L );
//		
//		Assert.assertEquals( "Hospede 1", hospede.getNome() );
//		Assert.assertEquals( "hospede1@mail.com", hospede.getEmail() );
//		Assert.assertEquals( "11111111111", hospede.getCpf() );
//		
//		hospede.setNome( "Hospede editado" );
//		hospede.setEmail( "hospede@pousadapower.com.br" );
//		hospede.setCpf( "78945612345" );
//		
//		hospede = this.pessoaService.updateHospede( hospede );
//		
//		Assert.assertTrue( hospede.getId() == 1000L );
//		
//		hospede = this.pessoaService.findHospedeById( 1000L );
//		Assert.assertEquals( hospede.getNome(), "Hospede editado" );
//		Assert.assertEquals( hospede.getEmail(), "hospede@pousadapower.com.br" );
//		Assert.assertEquals( hospede.getCpf(), "78945612345" );
//	}
//	
//	@Test( expected = DataIntegrityViolationException.class )
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void updateHospedeMustFail()
//	{
//		Hospede hospede = this.pessoaService.findHospedeById( 1000L );
//		
//		Assert.assertEquals( "Hospede 1", hospede.getNome() );
//		Assert.assertEquals( "hospede1@mail.com", hospede.getEmail() );
//		Assert.assertEquals( "11111111111", hospede.getCpf() );
//		
//		hospede = this.pessoaService.findHospedeById( 1001L );
//		
//		hospede.setCpf( "11111111111" );
//		
//		hospede = this.pessoaService.updateHospede( hospede );
//	}
//	
//	@Test
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void listHospedesByFiltersMustReturnAllHospedes()
//	{
//		Page<Hospede> hospedes = this.pessoaService.listHospedesByFilters( null, null );
//		Assert.assertTrue( hospedes.getTotalElements() == 3 );
//	}
//	
//	@Test
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
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
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void listHospedesByFiltersMustReturn0Hospede()
//	{
//		Page<Hospede> hospedes = this.pessoaService.listHospedesByFilters( "Não deve retornar registros", null );
//		Assert.assertTrue( hospedes.getTotalElements() == 0 );
//	}
//	
//	@Test
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void removeHospedeMustPass()
//	{
//		Page<Hospede> hospedes = this.pessoaService.listHospedesByFilters( null, null );
//		Assert.assertTrue( hospedes.getTotalElements() == 3 );
//		
//		this.pessoaService.removeHospede( 1000L );
//		
//		hospedes = this.pessoaService.listHospedesByFilters( null, null );
//		Assert.assertTrue( hospedes.getTotalElements() == 2 );
//		
//	}
//	
//	@Test( expected = EmptyResultDataAccessException.class )
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void removeHospedeMustFail()
//	{
//		this.pessoaService.removeHospede( 9999L );
//	}
//	
//	@Test
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void findHospedeByIdMustPass()
//	{
//		Hospede hospede = this.pessoaService.findHospedeById( 1000L );
//		
//		Assert.assertNotNull( hospede );
//		Assert.assertNotNull(  hospede.getId() );
//		
//		Assert.assertTrue( hospede.getId() == 1000L );
//	}
//	
//	@Test( expected = IllegalArgumentException.class )
//	@WithUserDetails("admin@email.com")
//	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
//		"/dataset/account/UserDataSet.xml",
//		"/dataset/endereco/PaisDataSet.xml",
//		"/dataset/endereco/EstadoDataSet.xml",
//		"/dataset/endereco/CidadeDataSet.xml",
//		"/dataset/hospede/HospedeDataSet.xml",
//	})
//	public void findHospedeByIdMustFail()
//	{
//		Hospede hospede = this.pessoaService.findHospedeById( 9999L );
//	}
	
}
