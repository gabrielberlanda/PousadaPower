/**
 * 
 */
package br.com.berlanda.pousadapower.domain.repository.hospedagem;

import java.util.Calendar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.hospedagem.Hospedagem;
import br.com.berlanda.pousadapower.domain.entity.hospedagem.StatusHospedagem;

/**
 * @author berlanda
 *
 */
public interface IHospedagemRepository extends JpaRepository<Hospedagem, Long>
{

	/**
	 * Os parametros dessa query não podem ser nulos!
	 * @param tipoQuartoId
	 * @param de
	 * @param ate
	 * @return
	 */
	@Query( value = "SELECT count (*) "
			+ "FROM Hospedagem hospedagem "
			+ "WHERE hospedagem.tipoQuarto.id = :tipoQuartoId "
			+ "AND ("
				+ "(hospedagem.entrada <= :de AND hospedagem.saida >= :ate ) OR "
				+ "( (hospedagem.entrada >= :de AND hospedagem.entrada <= :ate ) AND hospedagem.saida >= :ate ) OR "
				+ "( hospedagem.entrada <= :de AND ( hospedagem.saida <= :ate AND hospedagem.saida >= :de ) ) OR "
				+ "( hospedagem.entrada >= :de AND hospedagem.saida <= :ate ) "
			+ ")")
	public int countByTipoQuartoIdAndDate( @Param("tipoQuartoId") long tipoQuartoId, @Param("de") Calendar de, @Param("ate") Calendar ate );

	/**
	 * 
	 * @param hospedagemId
	 * @param tipoQuartoId
	 */
	@Query( value = "UPDATE Hospedagem h "
					+ "SET h.tipoQuarto.id = :tipoQuartoId, h.updated = now() "
					+ "WHERE h.id = :hospedagemId ")
	public void changeTipoQuartoReserva( @Param("hospedagemId") long hospedagemId, @Param("tipoQuartoId") long tipoQuartoId );
	
//	
//	@Query( value = "SELECT new Hospedagem() ")
//	public Page<Hospedagem> listByFilters( @Param("filter") String filter, @Param("de") Calendar de, @Param("ate") Calendar ate,
//			@Param("tipoQuartoId") Long tipoQuartoId, @Param("statusHospedagem") StatusHospedagem statusHospedagem, Pageable page );

	/**
	 * @param reservaId
	 */
	@Query( value = "UPDATE Hospedagem h "
					+ "SET h.statusHospedagem = :statusHospedagem "
					+ "WHERE h.id = :hospedagemId" )
	public void cancelarReserva( long hospedagemId, StatusHospedagem statusHospedagem );
}
