/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.quarto;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author berlanda
 *
 */
@DataTransferObject( type = "enum" )
public enum StatusQuarto
{
	LIMPO,
	SUJO;
}
