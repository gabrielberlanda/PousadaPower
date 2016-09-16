/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.pessoa.Pais;

/**
 * @author Gabriel Berlanda
 *
 */
public interface IPaisRepository extends JpaRepository<Pais, Long>
{
	
	@Query( value = "SELECT pais "
					+ "FROM Pais pais "
					+ "WHERE ( FILTER (pais.id, :filter) = TRUE  "
					+ "OR FILTER (pais.nome, :filter) = TRUE )")
	public Page<Pais> listByFilters (@Param("filter") String filter, Pageable page );
}
