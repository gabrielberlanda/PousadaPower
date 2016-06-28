/**
 * 
 */
package com.digows.blank.domain.repository.produto;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digows.blank.domain.entity.produto.Produto;

/**
 * @author Berlanda
 *
 */
public interface IProdutoRepository extends JpaRepository<Produto, Long>
{
	@Query( value = "SELECT new Produto( produto.id, produto.nome, produto.quantidadeEstoque, "
										+ "produto.quantidadeMinima, produto.precoUnitario, produto.precoCusto, "
										+ "produto.tipoProduto, produto.fornecedor.id, produto.fornecedor.razaoSocial, "
										+ "produto.fornecedor.nomeFantasia ) "
				+ "FROM Produto produto "
				+ "LEFT JOIN produto.fornecedor fornecedor "
				+ "WHERE ( FILTER (produto.id, :filter) = TRUE "
				+ "OR FILTER( produto.nome, :filter ) = TRUE "
				+ "OR FILTER( produto.tipoProduto, :filter ) = TRUE "
				+ "OR FILTER( fornecedor.nomeFantasia, :filter ) = TRUE "
				+ "OR FILTER( fornecedor.razaoSocial, :filter ) = TRUE "
				+ "OR FILTER( produto.precoUnitario, :filter ) = TRUE "
				+ "OR FILTER( produto.precoCusto, :filter ) = TRUE )")
	public Page<Produto> listByFilters ( @Param("filter") String filter, Pageable page );
	
	
}
