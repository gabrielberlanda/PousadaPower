/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.pessoa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.pessoa.Hospede;

/**
 * @author Gabriel Berlanda
 *
 */
public interface IHospedeRepository extends JpaRepository<Hospede, Long>
{
	@EntityGraph( attributePaths = {"cidade"})
	@Query( value =
			"FROM Hospede hospede "
			+ "LEFT JOIN hospede.cidade cidade "
			+ "WHERE ( FILTER (hospede.id, :filter) = TRUE "
				+ "OR FILTER( hospede.nome, :filter ) = TRUE "
				+ "OR FILTER( hospede.email, :filter) = TRUE "
				+ "OR FILTER( hospede.telefone, :filter ) = TRUE "
				+ "OR FILTER( hospede.cpf, :filter ) = TRUE "
				+ "OR FILTER( hospede.rg, :filter ) = TRUE "
				+ "OR FILTER( hospede.modeloVeiculo, :filter ) = TRUE "
				+ "OR FILTER( hospede.passaporte, :filter ) = TRUE "
				+ "OR FILTER( hospede.placa, :filter ) = TRUE "
				+ "OR FILTER( cidade.nome, :filter ) = TRUE ) " )
	public Page<Hospede> listHospedeByFilters ( @Param("filter") String filter, Pageable page );
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@EntityGraph( attributePaths = { "cidade.estado.pais.id"} )
	public Hospede findById( long id );
	
	
}
