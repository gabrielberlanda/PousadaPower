/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.hospedagem;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.hospedagem.Responsavel;

/**
 * @author berlanda
 *
 */
public interface IOrcamentoDiariaRepository extends JpaRepository<Responsavel, Long>
{

}
