/**
 * 
 */
package com.digows.blank.domain.entity.tipoquarto.tarifa;

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
}
