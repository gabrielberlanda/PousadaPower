/**
 * 
 */
package com.digows.blank.domain.entity.tipoquarto.tarifa;

import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author berlanda
 *
 */
@DataTransferObject(type = "enum")
public enum Dia
{
	DOMINGO,
	SEGUNDA,
	TERCA,
	QUARTA,
	QUINTA,
	SEXTA,
	SABADO;
	
	/**
	 * Retorna uma array com os dias ordenados
	 * 0 - Domingo;
	 * 1 - Segunda;
	 * ...
	 * 6 - Sabado;
	 * @return
	 */
	public static List<Dia> getDiasOrdenados()
	{
		List<Dia> dias = new ArrayList<Dia>();
		
		for ( Dia dia : Dia.values() )
		{
			dias.add( dia );
		}
		
		return dias;
	}
}
