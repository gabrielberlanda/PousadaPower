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

import br.com.berlanda.pousadapower.domain.entity.pessoa.Hospede;
import br.com.berlanda.pousadapower.domain.repository.pessoa.IHospedeRepository;
import br.com.berlanda.pousadapower.domain.repository.pessoa.IPessoaRepository;

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
	
		if ( hospede.getCidade() != null ) 
		{
			if ( hospede.getCidade().getEstado().getPais().isBrasil() ) {
				Assert.notNull( hospede.getCpf(), "CPF é obrigatório" );
			}
		}
		return this.hospedeRepository.save( hospede );
	}
	
	public Hospede updateHospede ( Hospede hospede )
	{
		Assert.notNull( hospede.getId(), "Hospede não cadastrado" );
		
		if ( hospede.getCidade() != null ) 
		{
			if ( hospede.getCidade().getEstado().getPais().isBrasil() ) {
				Assert.notNull( hospede.getCpf(), "CPF é obrigatório" );
			}
		}
		
		return this.hospedeRepository.save( hospede );
	}
	
	public Hospede findHospedeById ( Long hospedeId )
	{
		Assert.notNull(  hospedeId, "Informe o id do hospede!");
		
		Hospede hospede = new Hospede();
		
		hospede = this.hospedeRepository.findById( hospedeId );
		
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
