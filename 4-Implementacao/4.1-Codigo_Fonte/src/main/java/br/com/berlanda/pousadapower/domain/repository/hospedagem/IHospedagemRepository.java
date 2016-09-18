/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.hospedagem;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.hospedagem.Hospedagem;

/**
 * @author berlanda
 *
 */
public interface IHospedagemRepository extends JpaRepository<Hospedagem, Long>
{

}
