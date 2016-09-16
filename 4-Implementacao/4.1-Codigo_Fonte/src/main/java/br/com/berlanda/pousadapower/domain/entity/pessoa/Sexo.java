/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.pessoa;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author BERLANDA
 *
 */
@DataTransferObject(type = "enum")
public enum Sexo
{
	MASCULINO,
	FEMININO;
}
