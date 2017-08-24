/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.tipoquarto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.tipoquarto.TipoQuarto;

/**
 * @author berlanda
 *
 */
public interface ITipoQuartoRepository extends JpaRepository<TipoQuarto, Long>
{

	@EntityGraph( attributePaths = {
			"tarifasPadrao"
	})
	@Query( value = "SELECT DISTINCT tipoQuarto "
			+ "FROM TipoQuarto tipoQuarto "
					+ "WHERE FILTER(tipoQuarto.nome, :filter) = TRUE "
					+ "OR FILTER ( tipoQuarto.ocupacaoMaxima, :filter) = TRUE ")
	public Page<TipoQuarto> listByFilters ( @Param("filter") String filter, Pageable page );
	
	@Modifying
	@Query( value = "update TipoQuarto t set t.status = true WHERE t.id = :tipoQuartoId ")
	public void ativarTipoQuarto (@Param("tipoQuartoId") long tipoQuartoId );

	@Modifying
	@Query( value = "update TipoQuarto t set t.status = false WHERE t.id = :tipoQuartoId ")
	public void desativarTipoQuarto (@Param("tipoQuartoId") long tipoQuartoId );
	
	@EntityGraph( attributePaths = {
			"tarifasPadrao"
	})
	public TipoQuarto findById( long id );
	
	

}
