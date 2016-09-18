/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.hospedagem;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.hospedagem.OrcamentoDiaria;

/**
 * @author berlanda
 *
 */
public interface IResponsavelRepository extends JpaRepository<OrcamentoDiaria, Long>
{

}
