/**
 * 
 */
package br.com.berlanda.pousadapower.domain.service;

import javax.transaction.Transactional;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.berlanda.pousadapower.domain.entity.produto.Produto;
import br.com.berlanda.pousadapower.domain.repository.produto.IProdutoRepository;

/**
 * @author Berlanda
 *
 */
@Service
@RemoteProxy
@Transactional
public class ProdutoService
{
	
	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Autowired
	private IProdutoRepository produtoRepository;
	
	/*-------------------------------------------------------------------
	*							METODOS
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 * @param produto
	 * @return
	 */
	public Produto insertProduto ( Produto produto )
	{
		Assert.isNull( produto.getId(), "Produto já cadastrado" );
		
		produto.validarCampos();
		
		return this.produtoRepository.save( produto );
	}
	
	/**
	 * 
	 * @param produto
	 * @return
	 */
	public Produto updateProduto ( Produto produto )
	{
		Assert.notNull( produto.getId(), "Produto não cadastrado" );
		
		produto.validarCampos();
		
		return this.produtoRepository.save( produto );
	}
	
	/**
	 * 
	 * @param produtoId
	 * @return
	 */
	public Produto findProdutoById ( Long produtoId )
	{
		Assert.notNull( produtoId, "Informe o id do produto" );
		
		Produto produto = this.produtoRepository.findOne( produtoId );
		
		Assert.notNull( produto, "Produto não encontrado" );
		
		return produto;
	}
	
	/**
	 * 
	 * @param produtoId
	 */
	public void removeProduto ( Long produtoId )
	{
		Assert.notNull( produtoId, "Informe um produto a ser removido" );
		this.produtoRepository.delete( produtoId );
	}
	
	/**
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	public Page<Produto> listProdutosByFilters ( String filter, PageRequest page )
	{
		return this.produtoRepository.listByFilters( filter, page);
	}
	
	
}
