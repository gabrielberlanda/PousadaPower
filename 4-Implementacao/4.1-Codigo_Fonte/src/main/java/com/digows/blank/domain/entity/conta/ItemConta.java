/**
 * 
 */
package com.digows.blank.domain.entity.conta;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import com.digows.blank.domain.entity.hospedagem.Diaria;
import com.digows.blank.domain.entity.produto.Produto;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
//@Entity
@DataTransferObject( javascript = "ItemConta" )
public class ItemConta extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6195407107465722703L;
	
	/**
	 * 
	 */
	@NotNull
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private TipoItemConta tipo;
	
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private Diaria diaria;
	
	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private Produto produto;
	
}
