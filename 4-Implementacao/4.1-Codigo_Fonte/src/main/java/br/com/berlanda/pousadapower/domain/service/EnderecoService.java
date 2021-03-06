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

import br.com.berlanda.pousadapower.domain.entity.pessoa.Cidade;
import br.com.berlanda.pousadapower.domain.entity.pessoa.Estado;
import br.com.berlanda.pousadapower.domain.entity.pessoa.Pais;
import br.com.berlanda.pousadapower.domain.repository.pessoa.ICidadeRepository;
import br.com.berlanda.pousadapower.domain.repository.pessoa.IEstadoRepository;
import br.com.berlanda.pousadapower.domain.repository.pessoa.IPaisRepository;

/**
 * @author Gabriel Berlanda
 *
 */
@Service
@RemoteProxy
@Transactional
public class EnderecoService
{
	/**
	 * 
	 */
	@Autowired
	private IPaisRepository paisRepository;
	
	/**
	 * 
	 */
	@Autowired
	private IEstadoRepository estadoRepository;
	
	/**
	 * 
	 */
	@Autowired
	private ICidadeRepository cidadeRepository;
	
	/**
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	public Page<Pais> listPaisByFilters ( String filter, PageRequest page )
	{
		return this.paisRepository.listByFilters ( filter, page );
	}
	
	/**
	 * 
	 * @param filter
	 * @param paisId
	 * @param page
	 * @return
	 */
	public Page<Estado> listEstadoByFilters( String filter, Long paisId, PageRequest page )
	{
		return this.estadoRepository.listByFilters( filter, paisId, page );
	}
	
	/**
	 * 
	 * @param filter
	 * @param estadoId
	 * @param page
	 * @return
	 */
	public Page<Cidade> listCidadeByFilters ( String filter, Long estadoId, PageRequest page )
	{
		return this.cidadeRepository.listByFilters( filter, estadoId, page );
	}
}
