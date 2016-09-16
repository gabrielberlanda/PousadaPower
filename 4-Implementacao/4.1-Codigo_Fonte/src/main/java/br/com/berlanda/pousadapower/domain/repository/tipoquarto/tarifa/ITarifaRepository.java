/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.tipoquarto.tarifa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa.Tarifa;

/**
 * @author berlanda
 *
 */
public interface ITarifaRepository extends JpaRepository<Tarifa, Long> 
{

}
