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

import com.digows.blank.domain.entity.pessoa.Hospede;
import com.digows.blank.domain.repository.pessoa.IHospedeRepository;
import com.digows.blank.domain.repository.pessoa.IPessoaRepository;

/**
 * @author Gabriel Berlanda
 *
 */
@Service
@RemoteProxy
@Transactional
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

	
	/*-------------------------------------------------------------------
	*							METODOS
	*-------------------------------------------------------------------*/
	
	public Hospede insertHospede ( Hospede hospede )
	{
		Assert.isNull( hospede.getId(), "Hospede já cadastrado" );
		Assert.notNull( hospede.getNome(), "Campo nome não pode ser nulo" );
		Assert.notNull( hospede.getEmail(), "Campo email não pode ser nulo" );
		Assert.notNull( hospede.getTelefone(), "Campo telefone não pode ser Nulo" );
		
		return this.hospedeRepository.save( hospede );
	}
	
	public Hospede updateHospede ( Hospede hospede )
	{
		Assert.notNull( hospede.getId(), "Hospede não cadastrado" );
		Assert.notNull( hospede.getNome(), "Campo nome não pode ser nulo" );
		Assert.notNull( hospede.getEmail(), "Campo email não pode ser nulo" );
		Assert.notNull( hospede.getTelefone(), "Campo telefone não pode ser Nulo" );
		
		return this.hospedeRepository.save( hospede );
	}
	
	public Hospede findHospedeById ( Long hospedeId )
	{
		Assert.notNull(  hospedeId, "Informe o id do hospede!");
		
		Hospede hospede = new Hospede();
		
		hospede = this.hospedeRepository.findOne( hospedeId );
		
		Assert.notNull( hospede, "Hospede não encontrado" );
		
		return hospede;
		
	}
	
	public Page<Hospede> listHospedesByFilters ( String filter, PageRequest pageable )
	{
		
		return this.hospedeRepository.listHospedeByFilters( filter, pageable );
		
	}
	
	public void removeHospede ( Long hospedeId )
	{
		Assert.notNull(  hospedeId,"Informe um hospede para ser removido!" );
		this.hospedeRepository.delete( hospedeId );
	}
	
}
