/**
 * 
 */
package com.digows.blank.domain.repository.tipoquarto.tarifa;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digows.blank.domain.entity.tipoquarto.tarifa.TarifaExcecao;

/**
 * @author berlanda
 *
 */
public interface ITarifaExcecaoRepository extends JpaRepository<TarifaExcecao, Long>
{
//

//	
//	@Query("FROM TarifaExcecao tarifaExcecao "
//			+ "WHERE FILTER( tarifaExcecao.nome, :filter) = TRUE "
//			+ "AND ( :dataInicio != NULL OR :dataInicio = NULL ) "
//			+ "AND ( :dataFim != NULL OR :dataFim = NULL ) "
//			+ "AND ( tarifaExcecao.tipoQuarto.id = :tipoQuartoId )")
	@Query("FROM TarifaExcecao tarifaExcecao "
			+ "WHERE FILTER( tarifaExcecao.nome, :filter) = TRUE "
			+ "AND ( tarifaExcecao.dataInicio >= :dataInicio  OR CAST(:dataInicio as date) = NULL ) "
			+ "AND ( tarifaExcecao.dataFim <= :dataFim OR CAST(:dataFim as date) = NULL ) "
			+ "AND ( tarifaExcecao.tipoQuarto.id = :tipoQuartoId )")
	public Page<TarifaExcecao> listByFiltersAndTipoQuartoId ( @Param("filter") String filter, @Param("dataInicio") Calendar dataInicio, 
				@Param("dataFim") Calendar dataFim, @Param("tipoQuartoId") Long tipoQuartoId, Pageable page );
}
