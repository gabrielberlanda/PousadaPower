/**
 * 
 */
package br.com.berlanda.pousadapower.domain.service;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.berlanda.pousadapower.domain.entity.hospedagem.Hospedagem;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.StatusHospedagem;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.TipoQuarto;
import br.com.berlanda.pousadapower.domain.repository.hospedagem.IHospedagemRepository;
import br.com.berlanda.pousadapower.domain.repository.hospedagem.IOrcamentoDiariaRepository;
import br.com.berlanda.pousadapower.domain.repository.hospedagem.IResponsavelRepository;

/**
 * @author berlanda
 *
 */
@Service
@RemoteProxy
@Transactional
public class ReservaService
{

	/**
	 * Repositorios
	 *
	 */
	private IHospedagemRepository hospedagemRepository;
	
	private IOrcamentoDiariaRepository orcamentoRepository;
	
	private IResponsavelRepository responsavelRepository;
	/**
	 * Serviços
	 */
	
	/**
	 * 
	 * @param reservaId
	 * @return
	 */
	public Hospedagem findReservaById( long reservaId )
	{
		return null;
	}
	
	/**
	 * 
	 */
	public Page<Hospedagem> listReservasByFilters( String filter, Calendar chegadaDe, Calendar chegadaAte, Long tipoId, StatusHospedagem statusHospedagem )
	{
		return null;
		
	}
}
