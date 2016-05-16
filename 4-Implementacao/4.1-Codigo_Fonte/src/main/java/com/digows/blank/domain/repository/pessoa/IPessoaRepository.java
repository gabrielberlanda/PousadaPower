/**
 * 
 */
package com.digows.blank.domain.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digows.blank.domain.entity.pessoa.Pessoa;

/**
 * @author Gabriel Berlanda
 *
 */
public interface IPessoaRepository extends JpaRepository<Pessoa, Long>
{

}
