/**
 * 
 */
package com.digows.blank.domain.entity.pessoa;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.common.domain.entity.AbstractEntity;

/**
 * @author Gabriel Berlanda
 *
 */
@Entity
@DataTransferObject(javascript = "Pessoa")
public class Pessoa extends AbstractEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5295265388190591451L;
	
	/*
	 * Atributos
	 */

	private String name;
	
	private String rg;
	
	private String cpf;
	
	private String passaporte;
	
	private Calendar dataNascimento;
	
	private String email;
	
	private String telefone;
	
	private String celular;
	
	private Endereco endereco;
	
}
