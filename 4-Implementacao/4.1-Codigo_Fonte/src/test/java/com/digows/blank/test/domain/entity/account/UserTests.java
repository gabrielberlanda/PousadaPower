package com.digows.blank.test.domain.entity.account;

import org.junit.Assert;
import org.junit.Test;

import com.digows.blank.domain.entity.account.Usuario;
import com.digows.blank.domain.entity.account.PermissaoUsuario;
import com.digows.blank.test.domain.AbstractUnitTests;

/**
 * 
 * @author rodrigo.p.fraga@gmail.com
 * @since 09/05/2013
 * @version 1.0
 */
public class UserTests extends AbstractUnitTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	/**
     * 
     */
	@Test
	public void getAuthoritiesMustPass()
	{
		final Usuario usuario = new Usuario();
		usuario.setRole( PermissaoUsuario.ADMINISTRATOR );
		
		Assert.assertNotNull( usuario.getAuthorities() );
		Assert.assertTrue( usuario.getAuthorities().contains( PermissaoUsuario.ADMINISTRATOR ) );
		Assert.assertTrue( usuario.getAuthorities().contains( PermissaoUsuario.MANAGER ) );
		Assert.assertTrue( usuario.getAuthorities().contains( PermissaoUsuario.USER ) );
	}
}
