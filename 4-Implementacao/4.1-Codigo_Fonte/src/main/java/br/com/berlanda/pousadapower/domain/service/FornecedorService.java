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

import br.com.berlanda.pousadapower.domain.entity.fornecedor.Fornecedor;
import br.com.berlanda.pousadapower.domain.entity.pessoa.Cidade;
import br.com.berlanda.pousadapower.domain.entity.pessoa.Hospede;
import br.com.berlanda.pousadapower.domain.repository.fornecedor.IFornecedorRepository;

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
		
		final Fornecedor fornecedor = this.fornecedorRepository.findById( fornecedorId );
		
		Assert.notNull( fornecedor, "Fornecedor não encontrado" );
		
		return fornecedor;
	}
	
	/**
	 * 
	 * @param fornecedorId
	 */
	public void removeFornecedor ( long fornecedorId ) 
	{
		this.fornecedorRepository.delete( fornecedorId );
		this.fornecedorRepository.flush();
	}
	
	/**
	 * 
	 * @param fornecedor
	 * @return
	 */
	public Fornecedor insertFornecedor( Fornecedor fornecedor )
	{
		Assert.isNull( fornecedor.getId(), "Fornecedor já cadastrado");
		
		return this.fornecedorRepository.save( fornecedor );
	}
	
	/**
	 * 
	 * @param fornecedor
	 * @return
	 */
	public Fornecedor updateFornecedor( Fornecedor fornecedor )
	{
		Assert.notNull( fornecedor.getId(), "Id não pode ser nulo" );
		
		return this.fornecedorRepository.save( fornecedor );
	}
}
