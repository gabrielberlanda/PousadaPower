/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.hospedagem;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author berlanda
 *
 */
@DataTransferObject( type = "enum" )
public enum TipoHospedagem
{
	RESERVA_ANTECIPADA,
	SEM_RESERVA;
}
