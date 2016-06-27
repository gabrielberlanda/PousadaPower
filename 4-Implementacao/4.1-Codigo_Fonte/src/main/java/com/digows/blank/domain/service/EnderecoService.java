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

import com.digows.blank.domain.entity.pessoa.Cidade;
import com.digows.blank.domain.entity.pessoa.Estado;
import com.digows.blank.domain.entity.pessoa.Pais;
import com.digows.blank.domain.repository.pessoa.ICidadeRepository;
import com.digows.blank.domain.repository.pessoa.IEstadoRepository;
import com.digows.blank.domain.repository.pessoa.IPaisRepository;

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
