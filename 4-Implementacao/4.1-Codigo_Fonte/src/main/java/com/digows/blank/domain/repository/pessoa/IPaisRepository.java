/**
 * 
 */
package com.digows.blank.domain.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digows.blank.domain.entity.pessoa.Pais;

/**
 * @author Gabriel Berlanda
 *
 */
public interface IPaisRepository extends JpaRepository<Pais, Long>
{

}
