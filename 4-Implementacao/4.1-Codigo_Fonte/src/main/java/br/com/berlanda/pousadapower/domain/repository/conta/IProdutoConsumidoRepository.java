/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.conta;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.conta.ProdutoConsumido;

/**
 * @author berlanda
 *
 */
public interface IProdutoConsumidoRepository extends JpaRepository<ProdutoConsumido, Long>
{

}
