package br.com.berlanda.pousadapower.application.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.berlanda.pousadapower.domain.entity.account.Usuario;

/**
 *
 * @author rodrigo.p.fraga@gmail.com
 * @since 24/07/2013
 * @version 1.0
 * @category
 */
public class ContextHolder
{
	/*-------------------------------------------------------------------
	 * 		 						BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 *
	 * @return
	 */
	public static Usuario getAuthenticatedUser()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if ( authentication != null && authentication.getPrincipal() instanceof Usuario )
		{
			return ( Usuario ) authentication.getPrincipal();
		}

		throw new AuthenticationCredentialsNotFoundException( "There is no user authenticated." );
	}
}
