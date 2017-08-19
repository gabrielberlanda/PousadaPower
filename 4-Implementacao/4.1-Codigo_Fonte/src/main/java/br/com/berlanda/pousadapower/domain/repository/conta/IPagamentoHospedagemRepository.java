/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.conta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.conta.PagamentoHospedagem;

/**
 * @author berlanda
 *
 */
public interface IPagamentoHospedagemRepository extends JpaRepository<PagamentoHospedagem, Long>
{

	/**
	 * @param id
	 * @return
	 */
	List<PagamentoHospedagem> listByContaHospedagemId( Long id );

}
