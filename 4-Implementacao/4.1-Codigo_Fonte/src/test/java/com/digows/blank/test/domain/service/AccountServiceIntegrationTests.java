package com.digows.blank.test.domain.service;

import org.junit.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;

import com.digows.blank.domain.entity.account.Usuario;
import com.digows.blank.domain.entity.account.PermissaoUsuario;
import com.digows.blank.domain.service.AccountService;
import com.digows.blank.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * 
 * @author rodrigo.p.fraga@gmail.com
 */
public class AccountServiceIntegrationTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private AccountService accountService;
	
	/*-------------------------------------------------------------------
	 *				 		      TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void insertUserMustFail() 
	{
		this.accountService.insertUser( new Usuario() );
	}
	
	/**
	 * 
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void insertUserMustPass()
	{
		Usuario usuario = new Usuario( null, "Testing user", "test@user.com", true, PermissaoUsuario.USER, "user" );
		usuario = this.accountService.insertUser( usuario );

		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getId() );
		Assert.assertNotNull( usuario.getCreated() );
		Assert.assertTrue( usuario.getEnabled() );
		Assert.assertFalse( usuario.getPassword().equals( "user" ) );
	}
	
	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
    })
	public void findUserByIdMustPass()
	{
		final Usuario usuario = this.accountService.findUserById( 9999L );
	
		Assert.assertNotNull( usuario );
		Assert.assertNotNull( usuario.getId() );
		Assert.assertNotNull( usuario.getCreated() );
		Assert.assertEquals( "admin@email.com", usuario.getEmail() );
	}
	
	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void listUsersByFiltersMustReturn2()
	{
		final Page<Usuario> usuarios = this.accountService.listUsersByFilters( "user", null );
		
		Assert.assertNotNull( usuarios );
		Assert.assertEquals( 2, usuarios.getTotalElements() );
	}
	
	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void listUsersByFiltersMustReturn3()
	{
		final Page<Usuario> usuarios = this.accountService.listUsersByFilters( "1000,1001,xó", null );
		
		Assert.assertNotNull( usuarios );
		Assert.assertEquals( 3, usuarios.getTotalElements());
	}
	
	/**
	 * 
	 */
	@Test
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void listUsersByFiltersMustReturnAll()
	{
		final Page<Usuario> usuarios = this.accountService.listUsersByFilters( null, null );
		
		Assert.assertNotNull( usuarios );
		Assert.assertEquals( 4, usuarios.getTotalElements() );
	}
}