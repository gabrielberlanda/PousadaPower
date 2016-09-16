/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.pessoa.Cidade;
import br.com.berlanda.pousadapower.domain.entity.pessoa.Pais;

/**
 * @author Gabriel Berlanda
 *
 */
public interface ICidadeRepository extends JpaRepository<Cidade, Long>
{

	@Query ( value = "SELECT cidade "
			+ "FROM Cidade cidade "
			+ "WHERE cidade.estado.id = :estadoId "
			+ "AND ( "
				+ "FILTER( cidade.id, :filter ) = TRUE OR "
				+ "FILTER( cidade.nome, :filter ) = TRUE "
			+ ")")
	public Page<Cidade> listByFilters ( @Param("filter") String filter, @Param("estadoId") Long estadoId, Pageable page );
}
