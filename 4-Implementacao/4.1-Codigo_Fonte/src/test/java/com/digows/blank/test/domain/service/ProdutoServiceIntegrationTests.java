/**
 * 
 */
package com.digows.blank.test.domain.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;

import com.digows.blank.domain.entity.account.PermissaoUsuario;
import com.digows.blank.domain.entity.account.Usuario;
import com.digows.blank.domain.entity.fornecedor.Fornecedor;
import com.digows.blank.domain.entity.produto.Produto;
import com.digows.blank.domain.entity.produto.TipoProduto;
import com.digows.blank.domain.service.ProdutoService;
import com.digows.blank.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author Berlanda
 *
 */
public class ProdutoServiceIntegrationTests extends AbstractIntegrationTests
{
	
	@Autowired
	private ProdutoService produtoService;
	
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void insertProdutoMustPass()
	{
		Page<Produto> produtos = this.produtoService.listProdutosByFilters( null, null );
		Assert.assertTrue( produtos.getTotalElements() == 3 );
		
		Produto produto = new Produto();
		produto.setNome( "Produto de teste" );
		produto.setDescricao( "Produto de teste de integração" );
		produto.setPrecoCusto( 3.50 );
		produto.setPrecoUnitario( 5.0 );
		produto.setQuantidadeEstoque( 10 );
		produto.setQuantidadeMinima( 5 );
		produto.setTipoProduto( TipoProduto.SOUVENIER );
		produto.setFornecedor( new Fornecedor( 1000L ) );
		
		produto = this.produtoService.insertProduto( produto );
		
		Assert.assertNotNull( produto.getId() );
		
		produtos = this.produtoService.listProdutosByFilters( null, null );
		Assert.assertTrue( produtos.getTotalElements() == 4 );
		
	}
	
	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void insertProdutoMustFailWithNullFields()
	{
		Produto produto = new Produto();
		produto.setNome( "Produto de teste" );
		produto.setDescricao( "Produto de teste de integração" );
		produto.setQuantidadeEstoque( 10 );
		produto.setQuantidadeMinima( 5 );
		produto.setTipoProduto( TipoProduto.SOUVENIER );
		
		produto = this.produtoService.insertProduto( produto );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void updateProdutoMustPass()
	{
		Produto produto = this.produtoService.findProdutoById( 1000L );
		
		produto.setNome( "Produto de teste" );
		produto.setPrecoCusto( 100.00 );
		produto.setQuantidadeEstoque( 30 );
		produto = this.produtoService.updateProduto( produto );
		
		produto = this.produtoService.findProdutoById( 1000L );
		Assert.assertTrue( produto.getId() == 1000L );
		Assert.assertTrue( produto.getPrecoCusto() == 100.00 );
		Assert.assertEquals( produto.getNome(), "Produto de teste" );
		
	}
	
	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void updateProdutoMustFail()
	{
		Produto produto = this.produtoService.findProdutoById( 1000L );
		
		produto.setNome( "Produto de teste" );
		produto.setPrecoCusto( null );
		produto.setFornecedor( null );
		produto = this.produtoService.updateProduto( produto );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void findProdutoByIdMustPass()
	{
		Produto produto = this.produtoService.findProdutoById( 1000L );
		
		Assert.assertNotNull( produto );
		Assert.assertTrue( produto.getNome().equals( "Coca cola" ) );
	}
	
	@Test( expected = IllegalArgumentException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void findProdutoByIdMustFailWithInvalidProdutoId()
	{
		Produto produto = this.produtoService.findProdutoById( 9999L );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void removeProdutoMustPass()
	{
		Page<Produto> produtos = this.produtoService.listProdutosByFilters( null, null );
		Assert.assertTrue( produtos.getTotalElements() == 3 );
		
		this.produtoService.removeProduto( 1000L );
		
		produtos = this.produtoService.listProdutosByFilters( null, null );
		Assert.assertTrue( produtos.getTotalElements() == 2 );
	}
	
	@Test(expected = EmptyResultDataAccessException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void removeProdutoMustFail()
	{
		this.produtoService.removeProduto( 9999L );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void listProdutosByFiltersMustReturn1Produto()
	{
		Page<Produto> produtos = this.produtoService.listProdutosByFilters( "Doritos", null );
		Assert.assertTrue( produtos.getTotalElements() == 1 );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void listProdutosByFiltersMustReturnAllProdutoWithNullFilter()
	{
		Page<Produto> produtos = this.produtoService.listProdutosByFilters( null, null );
		Assert.assertTrue( produtos.getTotalElements() == 3 );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
		"/dataset/endereco/PaisDataSet.xml",
		"/dataset/endereco/EstadoDataSet.xml",
		"/dataset/endereco/CidadeDataSet.xml",
		"/dataset/fornecedor/FornecedorDataSet.xml",
		"/dataSet/produto/ProdutoDataSet.xml"
	})
	public void listProdutosByFiltersMustReturn0ProdutoWithInvalidFilter()
	{
		Page<Produto> produtos = this.produtoService.listProdutosByFilters( "Não deve retornar registros", null );
		Assert.assertTrue( produtos.getTotalElements() == 0 );
	}
}
