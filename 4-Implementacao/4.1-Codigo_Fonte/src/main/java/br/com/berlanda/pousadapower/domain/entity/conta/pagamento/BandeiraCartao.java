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
public enum BandeiraCartao
{
	VISA_CREDITO,
	MASTER_CREDITO,
	VISA_DEBITO,
	MASTER_DEBITO,
	AMEX,
	ELO_CREDITO,
	ELO_DEBITO;
}
