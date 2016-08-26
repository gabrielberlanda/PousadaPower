/**
 * 
 */
package com.digows.blank.domain.repository.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digows.blank.domain.entity.fornecedor.Fornecedor;

/**
 * @author Berlanda
 *
 */
public interface IFornecedorRepository extends JpaRepository<Fornecedor, Long>
{
	/**
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	@Query ( value = "SELECT fornecedor "
			+ "FROM Fornecedor fornecedor "
			+ "WHERE "
				+ "FILTER( fornecedor.id, :filter ) = TRUE OR "
				+ "FILTER( fornecedor.razaoSocial, :filter ) = TRUE OR "
				+ "FILTER( fornecedor.cnpj, :filter ) = TRUE OR "
				+ "FILTER( fornecedor.nomeFantasia, :filter ) = TRUE "
			+ ")")
	public Page<Fornecedor> listByFilters ( @Param("filter") String filter, Pageable page );
}
