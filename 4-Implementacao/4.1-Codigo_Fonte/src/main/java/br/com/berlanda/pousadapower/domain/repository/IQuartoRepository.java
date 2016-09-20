/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.quarto.Quarto;

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
}
