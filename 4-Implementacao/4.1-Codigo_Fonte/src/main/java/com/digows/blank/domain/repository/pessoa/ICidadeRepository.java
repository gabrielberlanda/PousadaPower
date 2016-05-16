/**
 * 
 */
package com.digows.blank.domain.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digows.blank.domain.entity.pessoa.Cidade;

/**
 * @author Gabriel Berlanda
 *
 */
public interface ICidadeRepository extends JpaRepository<Cidade, Long>
{

}
