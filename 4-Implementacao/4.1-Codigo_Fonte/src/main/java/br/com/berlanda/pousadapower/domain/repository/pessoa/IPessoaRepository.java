/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.berlanda.pousadapower.domain.entity.pessoa.Pessoa;

/**
 * @author Gabriel Berlanda
 *
 */
public interface IPessoaRepository extends JpaRepository<Pessoa, Long>
{

}
