/**
 * 
 */
package com.digows.blank.domain.service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.digows.blank.domain.entity.tipoquarto.TipoQuarto;
import com.digows.blank.domain.entity.tipoquarto.tarifa.Tarifa;
import com.digows.blank.domain.entity.tipoquarto.tarifa.TarifaExcecao;
import com.digows.blank.domain.repository.tipoquarto.ITipoQuartoRepository;
import com.digows.blank.domain.repository.tipoquarto.tarifa.ITarifaExcecaoRepository;

/**
 * @author berlanda
 *
 */
@Service
@RemoteProxy
@Transactional
public class TipoQuartoService
{
	/**
	 * 
	 */
	@Autowired
	private ITipoQuartoRepository tipoQuartoRepository;
	
	/**
	 * 
	 */
	@Autowired
	private ITarifaExcecaoRepository tarifaExceccaoRepository;
	
	/*
	 * SERVIÇOS
	 */
	
	/**
	 * 
	 * @param tipoQuarto
	 * @return
	 */
	public TipoQuarto insertTipoQuarto( TipoQuarto tipoQuarto )
	{
		Assert.notNull( tipoQuarto, "Informe um tipo de quarto para inserir" );
		Assert.isNull( tipoQuarto.getId(), "Tipo quarto já cadastrado." );
		Assert.notNull( tipoQuarto.getTarifasPadrao(), "Informe as tarifas padrão do quarto" );
		
		Tarifa.validarTarifaParaCadaDiaDaSemana( tipoQuarto.getTarifasPadrao() );
		
		tipoQuarto.setStatus( true );
		
		return this.tipoQuartoRepository.save( tipoQuarto );
	}
	
	/**
	 * 
	 * @param tipoQuarto
	 * @return
	 */
	public TipoQuarto updateTipoQuarto( TipoQuarto tipoQuarto )
	{
		Assert.notNull( tipoQuarto, "Informe um tipo de quarto para alterar" );
		Assert.notNull( tipoQuarto.getId(), "Tipo quarto não cadastrado" );
		Assert.notNull( tipoQuarto.getTarifasPadrao(), "Informe as tarifas padrão do quarto" );
		
		Tarifa.validarTarifaParaCadaDiaDaSemana( tipoQuarto.getTarifasPadrao() );
		
		tipoQuarto = this.tipoQuartoRepository.save( tipoQuarto );
		this.tipoQuartoRepository.flush();
		
		return tipoQuarto;
	}
	
	/**
	 * 
	 * @param tipoQuartoId
	 */
	public void removeTipoQuarto( long tipoQuartoId )
	{
		this.tarifaExceccaoRepository.delete( this.tarifaExceccaoRepository.listByFiltersAndTipoQuartoId( null, null, null, tipoQuartoId, null ) );
		this.tipoQuartoRepository.delete( tipoQuartoId );
		this.tipoQuartoRepository.flush();
	}
	
	/**
	 * 
	 * @param tipoQuartoId
	 * @return
	 */
	@Transactional( readOnly=true )
	public TipoQuarto findTipoQuartoById( long tipoQuartoId )
	{
		TipoQuarto tipoQuarto = this.tipoQuartoRepository.findOne( tipoQuartoId );
		
		Assert.notNull( tipoQuarto, "Tipo de Quarto não encontrado" );
		
		return tipoQuarto;
	}
	
	/**
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	@Transactional( readOnly=true )
	public Page<TipoQuarto> listTipoQuartosByFilters ( String filter, PageRequest page )
	{
		return this.tipoQuartoRepository.listByFilters( filter, page );
	}
	
	/**
	 * 
	 * @param filter
	 * @param tipoQuartoId
	 * @param page
	 * @return
	 */
	public Page<TarifaExcecao> listTarifaExcecoesByFiltersAndTipoQuartoId( String filter, Calendar dataInicio, Calendar dataFim, Long tipoQuartoId, PageRequest page )
	{
		return this.tarifaExceccaoRepository.listByFiltersAndTipoQuartoId( filter, dataInicio, dataFim, tipoQuartoId, page );
	}
	
	/**
	 * 
	 * @param tarifaExcecao
	 * @return
	 */
	public TarifaExcecao insertTarifaExcecao ( TarifaExcecao tarifaExcecao )
	{
		Assert.notNull( tarifaExcecao );
		Assert.isNull( tarifaExcecao.getId(), "Tarifa já cadastrada" );
		Assert.notNull( tarifaExcecao.getTarifas() );
		Assert.notNull( tarifaExcecao.getTipoQuarto() );
		
		Page<TarifaExcecao> tarifas = this.listTarifaExcecoesByFiltersAndTipoQuartoId( null, tarifaExcecao.getDataInicio(), tarifaExcecao.getDataFim(), tarifaExcecao.getTipoQuarto().getId(), null );
		
		Assert.isTrue( tarifas.getContent().size() == 0, "O tipo de quarto já possui tarifa de exceção entre esta data." );
		
		tarifaExcecao.validarDatas().validarTarifas();
		
		return this.tarifaExceccaoRepository.save( tarifaExcecao );
	}
	
	/**
	 * 
	 * @param tarifaExcecao
	 * @return
	 */
	public TarifaExcecao updateTarifaExcecao ( TarifaExcecao tarifaExcecao )
	{
		Assert.notNull( tarifaExcecao );
		Assert.notNull( tarifaExcecao.getId(), "Tarifa não cadastrada" );
		Assert.notNull( tarifaExcecao.getTarifas() );
		Assert.notNull( tarifaExcecao.getTipoQuarto() );

		Page<TarifaExcecao> tarifas = this.listTarifaExcecoesByFiltersAndTipoQuartoId( null, tarifaExcecao.getDataInicio(), tarifaExcecao.getDataFim(), tarifaExcecao.getTipoQuarto().getId(), null );

		
		Assert.isTrue( tarifas.getContent().size() <= 1, "O tipo de quarto já possui outra tarifa de exceção entre esta data." );
		
		if ( tarifas.getContent().size() == 1 )
		{
			Assert.isTrue( tarifas.getContent().get( 0 ).getId().equals( tarifaExcecao.getId() ) , "O tipo de quarto já possui outra tarifa de exceção entre esta data." );
		}
		
		tarifaExcecao.validarDatas().validarTarifas();
		
		return this.tarifaExceccaoRepository.save( tarifaExcecao );
	}
	
	/**
	 * 
	 * @param tarifaExcecao
	 * @return
	 */
	public TarifaExcecao findTarifaExcecaoById ( long tarifaExcecaoId )
	{
		TarifaExcecao tarifaExcecao = this.tarifaExceccaoRepository.findOne( tarifaExcecaoId );
		Assert.notNull( tarifaExcecao );
		Assert.notNull( tarifaExcecao.getTarifas() );
		return tarifaExcecao;
	}
	
	/**
	 * 
	 * @param tarifaExecaoId
	 */
	public void removeTarifaExcecao ( long tarifaExecaoId )
	{
		this.tarifaExceccaoRepository.delete( tarifaExecaoId );
	}
	
	/**
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	public List<Tarifa> listTarifasByDataInicioAndDataFim( Calendar dataInicio, Calendar dataFim )
	{
		return Tarifa.listTarifasByDataInicioAndDataFim( dataInicio, dataFim );
	}
	
	/**
	 * 
	 * @param tipoQuartoId
	 * @return
	 */
	public void desativarTipoQuarto ( long tipoQuartoId )
	{
		this.tipoQuartoRepository.desativarTipoQuarto( tipoQuartoId );
	}
	
	/**
	 * 
	 * @param tipoQuartoId
	 * @return
	 */
	public void ativarTipoQuarto ( long tipoQuartoId )
	{
		this.tipoQuartoRepository.ativarTipoQuarto( tipoQuartoId );
	}
	
}
