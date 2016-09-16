/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.tipoquarto.tarifa;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa.TarifaExcecao;

/**
 * @author berlanda
 *
 */
public interface ITarifaExcecaoRepository extends JpaRepository<TarifaExcecao, Long>
{

	@Query("FROM TarifaExcecao tarifaExcecao "
			+ "WHERE FILTER( tarifaExcecao.nome, :filter) = TRUE "
			+ "AND ( "
				+ " ( CAST(:dataInicio as date ) = NULL AND CAST(:dataFim as date ) = NULL ) OR "
				+ "	( tarifaExcecao.dataInicio <= :dataInicio AND tarifaExcecao.dataFim >= :dataFim ) OR "
				+ " ( (tarifaExcecao.dataInicio >= :dataInicio AND tarifaExcecao.dataInicio <= :dataFim ) AND tarifaExcecao.dataFim >= dataFim ) OR "
				+ " ( tarifaExcecao.dataInicio <= :dataInicio AND ( tarifaExcecao.dataFim <= :dataFim AND tarifaExcecao.dataFim >= :dataInicio ) ) OR "
				+ " ( tarifaExcecao.dataInicio >= :dataInicio AND tarifaExcecao.dataFim <= :dataFim )"
			+ ")"
			+ "AND ( tarifaExcecao.tipoQuarto.id = :tipoQuartoId )")
	public Page<TarifaExcecao> listByFiltersAndTipoQuartoId ( @Param("filter") String filter, @Param("dataInicio") Calendar dataInicio, 
				@Param("dataFim") Calendar dataFim, @Param("tipoQuartoId") Long tipoQuartoId, Pageable page );
}
