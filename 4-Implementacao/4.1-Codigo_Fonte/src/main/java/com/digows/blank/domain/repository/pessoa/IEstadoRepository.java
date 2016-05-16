/**
 * 
 */
package com.digows.blank.domain.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digows.blank.domain.entity.pessoa.Estado;

/**
 * @author Gabriel Berlanda
 *
 */
public interface IEstadoRepository extends JpaRepository<Estado, Long>
{

}
