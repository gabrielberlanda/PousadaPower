/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.conta;

import java.io.Serializable;

import javax.persistence.Entity;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
//@Entity
@DataTransferObject( javascript = "Conta" )
public class Conta extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2518514557023857190L;
	
}
