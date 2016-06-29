/**
 * 
 */
package com.digows.blank.domain.service;

import javax.transaction.Transactional;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.digows.blank.domain.entity.fornecedor.Fornecedor;
import com.digows.blank.domain.entity.pessoa.Cidade;
import com.digows.blank.domain.entity.pessoa.Hospede;
import com.digows.blank.domain.repository.fornecedor.IFornecedorRepository;

/**
 * @author Berlanda
 *
 */
@Service
@RemoteProxy
@Transactional
public class FornecedorService
{

	/**
	 * 
	 */
	@Autowired
	private IFornecedorRepository fornecedorRepository;
	
	/**
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	public Page<Fornecedor> listFornecedoresByFilters ( String filter,  PageRequest page )
	{
		return this.fornecedorRepository.listByFilters( filter, page );
	}
	
	/**
	 * 
	 * @param fornecedorId
	 * @return
	 */
	public Fornecedor findFornecedorById ( Long fornecedorId )
	{
		Assert.notNull(  fornecedorId, "Informe o id do fornecedor!");
		
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor = this.fornecedorRepository.findOne( fornecedorId );
		
		Assert.notNull( fornecedor, "Fornecedor não encontrado" );
		
		return fornecedor;
		
	}
}