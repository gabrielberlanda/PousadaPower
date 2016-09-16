/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.produto;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author Berlanda
 *
 */
@DataTransferObject(type = "enum")
public enum TipoProduto
{
	BEBIDA,
	REFEICAO,
	SOUVENIER;
}
