/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.tipoquarto.tarifa;

import java.util.ArrayList;
import java.util.Calendar;
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
	
	/**
	 * A constant de dia do tipo de dado Calendar começa de Domingo até Sabado, mas o valor de Domingo é 1, Segunda é 2 ... Sabado é 7.
	 * Esse método faz um parse do valor.
	 * @param dayOfWeek
	 * @return
	 */
	public static Dia parseToCalendarDayOfWeek( Integer dayOfWeek )
	{
		switch ( dayOfWeek )
		{
			case 1:
				return Dia.DOMINGO;
			case 2:
				return Dia.SEGUNDA;
			case 3: 
				return Dia.TERCA;
			case 4:
				return Dia.QUARTA;
			case 5:
				return Dia.QUINTA;
			case 6:
				return Dia.SEXTA;
			case 7:
				return Dia.SABADO;
			default:
				break;
		}
		return null;
	}
	
	/**
	 * O metodo faz um parse do valor deste enum com o valor do dayOfWeek do date time, no date time a constante 1 representa segunda e a 7 representa domingo.
	 * @param dayOfWeek
	 * @return
	 */
	public static Dia parseToDateTimeDayOfWeek( Integer dayOfWeek )
	{
		switch ( dayOfWeek )
		{
			case 1:
				return Dia.SEGUNDA;
			case 2:
				return Dia.TERCA;
			case 3: 
				return Dia.QUARTA;
			case 4:
				return Dia.QUINTA;
			case 5:
				return Dia.SEXTA;
			case 6:
				return Dia.SABADO;
			case 7:
				return Dia.DOMINGO;
			default:
				break;
		}
		return null;
	}
}
