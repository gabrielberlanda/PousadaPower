package com.digows.blank.domain.entity.account;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author rodrigo.p.fraga@gmail.com
 * @since 02/06/2014
 * @version 1.0
 */
@DataTransferObject(type = "enum")
public enum PermissaoUsuario implements GrantedAuthority
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	ADMINISTRADOR,
	RECEPCIONISTA,
	CAIXA;

	public static final String ADMINISTRADOR_VALUE 	= "ADMINISTRADOR";
	public static final String RECEPCIONISTA_VALUE 	= "RECEPCIONISTA";
	public static final String CAIXA_VALUE 			= "CAIXA";
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority()
	{
		return this.name();
	}
}