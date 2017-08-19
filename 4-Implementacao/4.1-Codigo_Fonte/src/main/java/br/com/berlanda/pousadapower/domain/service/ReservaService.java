/**
 * 
 */
package br.com.berlanda.pousadapower.domain.service;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.berlanda.pousadapower.application.security.ContextHolder;
import br.com.berlanda.pousadapower.domain.entity.conta.ContaHospedagem;
import br.com.berlanda.pousadapower.domain.entity.conta.PagamentoHospedagem;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.Hospedagem;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.StatusHospedagem;
import br.com.berlanda.pousadapower.domain.entity.quarto.StatusQuarto;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.TipoQuarto;
import br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa.TarifaExcecao;
import br.com.berlanda.pousadapower.domain.repository.IQuartoRepository;
import br.com.berlanda.pousadapower.domain.repository.conta.IContaHospedagemRepository;
import br.com.berlanda.pousadapower.domain.repository.conta.IPagamentoHospedagemRepository;
import br.com.berlanda.pousadapower.domain.repository.hospedagem.IHospedagemRepository;
import br.com.berlanda.pousadapower.domain.repository.hospedagem.IOrcamentoDiariaRepository;
import br.com.berlanda.pousadapower.domain.repository.hospedagem.IResponsavelRepository;
import br.com.berlanda.pousadapower.domain.repository.tipoquarto.ITipoQuartoRepository;
import br.com.berlanda.pousadapower.domain.repository.tipoquarto.tarifa.ITarifaExcecaoRepository;

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
	@Autowired
	private IHospedagemRepository hospedagemRepository;
	@Autowired
	private IOrcamentoDiariaRepository orcamentoRepository;
	@Autowired
	private IResponsavelRepository responsavelRepository;
	@Autowired
	private ITipoQuartoRepository tipoQuartoRepository;
	@Autowired
	private ITarifaExcecaoRepository tarifaExcecaoRepository;
	@Autowired
	private IQuartoRepository quartoRepository;
	@Autowired
	private IContaHospedagemRepository contaHospedagemRepository;
	@Autowired
	private IPagamentoHospedagemRepository pagamentoHospedagemRepository;
	
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
		Hospedagem reserva = this.hospedagemRepository.findOne( reservaId );
		
		Assert.notNull( reserva, "Reserva não encontrada" );
		
		return reserva;
	}
	
	/**
	 * 
	 */
	public Page<Hospedagem> listReservasByFilters( String filter, Calendar chegadaDe, Calendar chegadaAte, Long tipoId, StatusHospedagem statusHospedagem )
	{
		return null;
	}
	
	public void cancelarReserva ( long reservaId )
	{
		Hospedagem reserva = this.findReservaById( reservaId );
		
		List<PagamentoHospedagem> pagamentosHospedagem = this.pagamentoHospedagemRepository.listByContaHospedagemId ( reserva.getContaHospedagem().getId() );
		
		Double total = 0d;
		for ( PagamentoHospedagem pagamentoHospedagem : pagamentosHospedagem )
		{
			total =+ pagamentoHospedagem.getPagamento().getValor();
		}
		
		Assert.isTrue( total == 0d, "A reserva possui pagamentos ou pendências e não pode ser cancelada." );
		
		this.hospedagemRepository.cancelarReserva ( reservaId, StatusHospedagem.CANCELADA );
	}
	
	/**
	 * 
	 * @param reserva
	 * @return
	 */
	public Hospedagem insertReserva ( Hospedagem reserva )
	{
		int quantidadeDisponivel = this.verificarQuantidadeQuartoDisponivelPorTipoQuartoId( reserva.getEntrada(), reserva.getSaida(), reserva.getTipoQuarto().getId(), null );
		Assert.isTrue( quantidadeDisponivel > 0, "Impossivel inserir a reserva pois não possui disponibilidade para a data." );
		Assert.isTrue( reserva.getEntrada().before( reserva.getSaida() ), "A data de entrada não pode ser maior que a de saída." );
		
		TipoQuarto tipoQuarto = this.tipoQuartoRepository.findOne( reserva.getTipoQuarto().getId() );
		List<TarifaExcecao> tarifasExcecao = 
				this.tarifaExcecaoRepository.listByFiltersAndTipoQuartoId( null, reserva.getEntrada(), reserva.getSaida(), tipoQuarto.getId(), null ).getContent();
		
		reserva.gerarOrcamentoTarifa( tipoQuarto, tarifasExcecao );

		reserva.setReservadoPor( ContextHolder.getAuthenticatedUser() );
		reserva.generateCodigo();
		
		reserva = this.hospedagemRepository.save( reserva );
		
		ContaHospedagem contaHospedagem = new ContaHospedagem();
		contaHospedagem.setHospedagem( reserva );
		
		this.contaHospedagemRepository.save( contaHospedagem );
		
		return reserva;
	}
	
	/**
	 * 
	 * @param reserva
	 * @return
	 */
	public Hospedagem updateReserva ( Hospedagem reserva )
	{
		
		return reserva;
	} 
	
	/**
	 * Altera o tipo de quarto da reserva
	 */
	public void updateReservaTipoQuarto( Long reservaId, Long tipoQuartoId )
	{
		Hospedagem reserva = this.hospedagemRepository.findOne( reservaId );
		
		Assert.isTrue( reserva.getTipoQuarto().getId() != tipoQuartoId, "A hospedagem já está com este tipo de quarto." );
		
		int quantidadeDisponivel = this.verificarQuantidadeQuartoDisponivelPorTipoQuartoId( reserva.getEntrada(), reserva.getSaida(),
				tipoQuartoId, null );
		
		Assert.isTrue( quantidadeDisponivel > 0, "Impossivel alterar o tipo de quarto da reserva, não possuimos disponibilidade para esta data." );
		
		this.hospedagemRepository.changeTipoQuartoReserva( reserva.getId(), tipoQuartoId );
	}
	
	/**
	 * 
	 * @param tipoQuartoId
	 * @return
	 */
	public int verificarQuantidadeQuartoDisponivelPorTipoQuartoId( Calendar dataInicio, Calendar dataFim, Long tipoQuartoId, StatusQuarto statusQuarto )
	{
		int totalQuartoDisponivel = this.quartoRepository.countQuartoByTipoQuartoId( tipoQuartoId, statusQuarto );
		int totalQuartoReservado = this.hospedagemRepository.countByTipoQuartoIdAndDate( tipoQuartoId, dataInicio, dataFim );
		int totalDisponivel = totalQuartoDisponivel - totalQuartoReservado;
	
		return totalDisponivel > 0 ? totalDisponivel : 0;
 	}
	
	
	/**
	 * 
	 */
	//TODO verificar esse cron expression 
	@Scheduled( cron = "0 0 12 * * ?")
	public void cancelarReservasVencidas()
	{
		Calendar today = Calendar.getInstance();
//		Page<Hospedagem> hospedagens = this.hospedagemRepository.listByFilters( null, today, today, null, StatusHospedagem.RESERVADA, null );
		Page<Hospedagem> hospedagens = null; //TODO verificar isso aqi plsssss implmentai po
		
		for ( Hospedagem hospedagem : hospedagens )
		{
			hospedagem.setStatusHospedagem( StatusHospedagem.CANCELADA );
			this.hospedagemRepository.save( hospedagem );
		}
	}
	
	//@Scheduled() //Estudar.
/*	@Scheduled( fixedDelay = 10000 )
	public void teste ()
	{
		System.out.println( "teste" );
	}*/
}
