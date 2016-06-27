/**
 * 
 */
package com.digows.blank.domain.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digows.blank.domain.entity.pessoa.Estado;

/**
 * @author Gabriel Berlanda
 *
 */
public interface IEstadoRepository extends JpaRepository<Estado, Long>
{
	
	@Query ( value = "SELECT estado "
			+ "FROM Estado estado "
			+ "WHERE estado.pais.id = :paisId "
			+ "AND ( FILTER( estado.id, :filter ) = TRUE "
			+ "OR FILTER( estado.nome, :filter ) = TRUE )" )
	public Page<Estado> listByFilters ( @Param("filter") String filter, @Param("paisId") Long paisId, Pageable page );
	
}
