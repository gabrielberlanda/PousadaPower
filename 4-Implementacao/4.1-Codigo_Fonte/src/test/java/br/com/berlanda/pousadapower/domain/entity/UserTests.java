package br.com.berlanda.pousadapower.domain.entity;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import br.com.berlanda.pousadapower.domain.AbstractUnitTests;
import br.com.berlanda.pousadapower.domain.entity.account.PermissaoUsuario;
import br.com.berlanda.pousadapower.domain.entity.account.Usuario;

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
		usuario.setRole( PermissaoUsuario.ADMINISTRADOR );
		
		Assert.assertNotNull( usuario.getAuthorities() );
		Assert.assertTrue( usuario.getAuthorities().contains( PermissaoUsuario.ADMINISTRADOR ) );
		Assert.assertTrue( usuario.getAuthorities().contains( PermissaoUsuario.CAIXA ) );
		Assert.assertTrue( usuario.getAuthorities().contains( PermissaoUsuario.RECEPCIONISTA ) );
		
	}
}
