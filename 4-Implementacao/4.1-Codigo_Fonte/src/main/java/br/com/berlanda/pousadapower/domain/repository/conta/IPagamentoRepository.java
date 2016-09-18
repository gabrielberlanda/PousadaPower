/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.conta;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.conta.pagamento.Pagamento;

/**
 * @author berlanda
 *
 */
public interface IPagamentoRepository extends JpaRepository<Pagamento, Long>
{

}
