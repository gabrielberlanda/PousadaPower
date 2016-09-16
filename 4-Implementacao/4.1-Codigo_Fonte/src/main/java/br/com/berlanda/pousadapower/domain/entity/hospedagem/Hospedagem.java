/**
 * 
 */
package br.com.berlanda.pousadapower.domain.entity.hospedagem;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author berlanda
 *
 */
//@Entity
@DataTransferObject( javascript= "Hospedagem" )
public class Hospedagem extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2331752523612587948L;
	
	@NotNull
	private Calendar entrada;
	
	@NotNull
	private Calendar saida;
	
	private String observacao;

	@NotNull
	private String codigo;

	@NotNull( message= "Informe um responsavel" )
	@OneToOne( cascade= CascadeType.ALL, fetch= FetchType.EAGER )
	private Responsavel responsavel;
	
	
	
}
