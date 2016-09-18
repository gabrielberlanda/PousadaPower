/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.conta.pagamento;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author berlanda
 *
 */
@DataTransferObject( type = "enum" )
public enum TipoPagamento
{
	REAL,
	DOLAR,
	PESO,
	GUARANI,
	CREDITO,
	DEBITO;
}
