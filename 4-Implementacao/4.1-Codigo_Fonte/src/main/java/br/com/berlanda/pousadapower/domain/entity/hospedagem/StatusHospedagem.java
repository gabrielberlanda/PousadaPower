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
public enum StatusHospedagem
{
	CHECK_IN,
	CHECK_OUT,
	RESERVADA,
	CANCELADA;
}
