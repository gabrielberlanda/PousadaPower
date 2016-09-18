/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.conta;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.conta.ContaHospedagem;

/**
 * @author berlanda
 *
 */
public interface IContaHospedagemRepository extends JpaRepository<ContaHospedagem, Long>
{

}
