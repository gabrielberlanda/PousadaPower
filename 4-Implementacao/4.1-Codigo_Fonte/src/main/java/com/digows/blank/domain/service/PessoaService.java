/**
 * 
 */
package com.digows.blank.domain.service;

import org.directwebremoting.annotations.RemoteProxy;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.digows.blank.domain.entity.pessoa.Hospede;
import com.digows.blank.domain.repository.pessoa.IHospedeRepository;
import com.digows.blank.domain.repository.pessoa.IPessoaRepository;

/**
 * @author Gabriel Berlanda
 *
 */

@Service
@RemoteProxy
public class PessoaService
{

	/*-------------------------------------------------------------------
	*				 		     ATRIBUTOS
	*-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	/**
	 * 
	 */
	@Autowired
	private IHospedeRepository hospedeRepository;

//	/**
//	 * 
//	 */
//	@Autowired
//	private EnderecoService enderecoService;
	
	/*-------------------------------------------------------------------
	*							METODOS
	*-------------------------------------------------------------------*/
	
	public Hospede inserirHospede ( Hospede hospede )
	{
		Assert.assertNull( "Hospede já cadastrado", hospede.getId() );
		Assert.assertNotNull( "Campo nome não pode ser nulo" , hospede.getName() );
		Assert.assertNotNull( "Campo email não pode ser nulo", hospede.getEmail() );
		Assert.assertNotNull( "Campo telefone não pode ser Nulo", hospede.getEmail() );
		
		return this.hospedeRepository.save( hospede );
	}
	
	public Hospede atualizarHospede ( Hospede hospede )
	{
		Assert.assertNotNull( "Hospede não cadastrado", hospede.getId() );
		Assert.assertNotNull( "Campo nome não pode ser nulo" , hospede.getName() );
		Assert.assertNotNull( "Campo email não pode ser nulo", hospede.getEmail() );
		Assert.assertNotNull( "Campo telefone não pode ser Nulo", hospede.getEmail() );
		
		return this.hospedeRepository.save( hospede );
	}
	
	public Hospede findHospedeById ( Long hospedeId )
	{
		Assert.assertNotNull( "Informe o id do hospede!", hospedeId);
		
		Hospede hospede = new Hospede();
		
		hospede = this.hospedeRepository.findOne( hospedeId );
		
		Assert.assertNotNull( "Hospede não encontrado", hospede.getId() );
		
		return hospede;
		
	}
	
	public Page<Hospede> listHospedeByFilters ( String filter, PageRequest pageable )
	{
		
		return this.hospedeRepository.listHospedeByFilters( filter, pageable );
		
	}
	
	public void removerHospede ( Long hospedeId )
	{
		Assert.assertNotNull( "Informe um hospede para ser removido!", hospedeId );
		this.hospedeRepository.delete( hospedeId );
	}
	
}
