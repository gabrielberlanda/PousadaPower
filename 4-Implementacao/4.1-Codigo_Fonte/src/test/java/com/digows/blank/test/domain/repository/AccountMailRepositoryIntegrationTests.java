package com.digows.blank.test.domain.repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.digows.blank.test.domain.AbstractIntegrationTests;

import br.com.berlanda.pousadapower.domain.entity.account.Usuario;
import br.com.berlanda.pousadapower.domain.repository.IAccountMailRepository;

/**
 * 
 * @author rodrigo.p.fraga@gmail.com
 * @since 09/05/2013
 * @version 1.0
 */
public class AccountMailRepositoryIntegrationTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
     * 
     */
	@Autowired
	private IAccountMailRepository accountMailRepository;

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * @throws ExecutionException 
	 * @throws InterruptedException 
     * 
     */
	@Test
	public void sendNewUserAccountTestMustPass() throws InterruptedException, ExecutionException
	{
		final Usuario usuario = new Usuario();
		usuario.setEmail( "eits@mailinator.com" );
		usuario.setName( "Suporte da eits" );

		final Future<Void> emailSent = this.accountMailRepository.sendNewUserAccount( usuario );
		
		Assert.assertNotNull( emailSent );
		Assert.assertNull( emailSent.get() );
	}
}
