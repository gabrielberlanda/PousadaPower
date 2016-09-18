/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.conta;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.conta.PagamentoHospedagem;

/**
 * @author berlanda
 *
 */
public interface IPagamentoHospedagemRepository extends JpaRepository<PagamentoHospedagem, Long>
{

}
