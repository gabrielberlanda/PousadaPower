/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.quarto.Quarto;
import br.com.berlanda.pousadapower.domain.entity.quarto.StatusQuarto;

/**
 * @author berlanda
 *
 */
public interface IQuartoRepository extends JpaRepository<Quarto, Long>
{
	
	 @Query(value="SELECT new Quarto( quarto.id, quarto.nome, quarto.tipoQuarto.id, quarto.tipoQuarto.nome, quarto.status, quarto.ativo ) " +
			  "FROM Quarto quarto " +
			  "WHERE ( (FILTER(quarto.id, :filter) = TRUE " 
			 	+ "OR FILTER(quarto.nome, :filter) = TRUE "
			 	+ "OR FILTER(quarto.tipoQuarto.nome, :filter) = TRUE) )")
	public Page<Quarto> listByFilters( @Param("filter") String filter, Pageable page);

	/**
	 * 
	 * @param tipoQuartoId
	 * @return
	 */
	@Query( value = "SELECT count(*) "
					+ "FROM Quarto quarto "
					+ "WHERE quarto.tipoQuarto.id = :tipoQuartoId "
					+ "AND quarto.ativo = TRUE "
					+ "AND ( :statusQuarto = NULL OR quarto.status = :statusQuarto )" )
	public int countQuartoByTipoQuartoId( @Param("tipoQuartoId") long tipoQuartoId, @Param("statusQuarto") StatusQuarto statusQuarto );
	
	@EntityGraph( attributePaths = {
			"tipoQuarto"
	})
	public Quarto findById( long quartoId );
}
