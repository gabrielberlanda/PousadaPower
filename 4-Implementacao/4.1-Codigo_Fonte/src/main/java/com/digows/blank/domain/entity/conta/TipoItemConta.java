/**
 * 
 */
package com.digows.blank.domain.entity.conta;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author berlanda
 *
 */
@DataTransferObject( type = "enum" )
public enum TipoItemConta
{
	DIARIA,
	PRODUTO;
}
