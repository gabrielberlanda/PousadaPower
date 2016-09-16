package com.digows.blank.test.domain.service;


import org.junit.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;

import com.digows.blank.test.domain.AbstractIntegrationTests;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.com.berlanda.pousadapower.domain.entity.account.PermissaoUsuario;
import br.com.berlanda.pousadapower.domain.entity.account.Usuario;
import br.com.berlanda.pousadapower.domain.service.AccountService;

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
		Usuario usuario = new Usuario( null, "Testing user", "test@user.com", true, PermissaoUsuario.CAIXA, "user" );
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
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void activateUserMustPass()
	{
		Usuario usuario = this.accountService.findUserById( 1000L );
		
		Assert.assertFalse( usuario.getEnabled() );
		
		this.accountService.activateUser( 1000L );
		
		usuario = this.accountService.findUserById( 1000L );
		
		Assert.assertTrue( usuario.getEnabled() );
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void deactivateUserMustPass()
	{
		Usuario usuario = this.accountService.findUserById( 9999L );
		
		Assert.assertTrue( usuario.getEnabled() );
		
		this.accountService.deactivateUser( 9999L );
		
		usuario = this.accountService.findUserById( 9999L );
		
		Assert.assertFalse( usuario.getEnabled() );
	}
	@Test
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void updateUserByAdminMustPass() 
	{
		Usuario usuario = this.accountService.findUserById( 1001L );
		
		Assert.assertEquals( usuario.getName(), "User 002" );
		Assert.assertEquals( usuario.getPassword(), "d1bd2f08fead38a982aed9d4ca060152400b1b8f" );
		Assert.assertEquals( usuario.getEmail(), "user002@testing.com" );
		
		usuario = this.accountService.updateUserByAdmin( usuario.getId(), "Editado pelo admin", "email@edited.com", PermissaoUsuario.ADMINISTRADOR, true , "teste123", "teste123" );
		
		Assert.assertEquals( usuario.getName(), "Editado pelo admin");
		Assert.assertEquals( usuario.getEmail(), "email@edited.com");
		Assert.assertEquals( usuario.getRole(), PermissaoUsuario.ADMINISTRADOR );
		Assert.assertEquals( usuario.isEnabled(), true );
		Assert.assertTrue( !usuario.getPassword().equals( "d1bd2f08fead38a982aed9d4ca060152400b1b8f" ) );
	}
	
	@Test( expected = AccessDeniedException.class )
	@WithUserDetails("user002@testing.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void updateUserByAdminMustFailWithNotAdminUser() 
	{
		Usuario usuario = this.accountService.findUserById( 1001L );
		
		Assert.assertEquals( usuario.getName(), "User 002" );
		Assert.assertEquals( usuario.getPassword(), "d1bd2f08fead38a982aed9d4ca060152400b1b8f" );
		Assert.assertEquals( usuario.getEmail(), "user002@testing.com" );
		
		usuario = this.accountService.updateUserByAdmin( usuario.getId(), "Editado pelo admin", "email@edited.com", PermissaoUsuario.ADMINISTRADOR, true , "teste123", "teste123" );
		
		Assert.assertEquals( usuario.getName(), "Editado pelo admin");
		Assert.assertEquals( usuario.getEmail(), "email@edited.com");
		Assert.assertEquals( usuario.getRole(), PermissaoUsuario.ADMINISTRADOR );
		Assert.assertEquals( usuario.isEnabled(), true );
		Assert.assertTrue( !usuario.getPassword().equals( "d1bd2f08fead38a982aed9d4ca060152400b1b8f" ) );

	}
	
	@Test
	@WithUserDetails("user002@testing.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void updateProfileMustPass() 
	{
		Usuario usuario = this.accountService.findUserById( 1001L );
		
		Assert.assertEquals( usuario.getName(), "User 002" );
		Assert.assertEquals( usuario.getPassword(), "d1bd2f08fead38a982aed9d4ca060152400b1b8f" );
		Assert.assertEquals( usuario.getEmail(), "user002@testing.com" );
		
		usuario = this.accountService.updateProfile( 1001L, "usuario editado", "a@a", null, null, null );
		
		Assert.assertEquals( usuario.getName(), "usuario editado" );
		Assert.assertEquals( usuario.getEmail(), "a@a" );
	}
	
	@Test( expected = AccessDeniedException.class )
	@WithUserDetails("admin@email.com")
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void updateProfileMustFailWithoutAuthenticatedUser() 
	{
		Usuario usuario = this.accountService.findUserById( 1001L );
		
		Assert.assertEquals( usuario.getName(), "User 002" );
		Assert.assertEquals( usuario.getPassword(), "d1bd2f08fead38a982aed9d4ca060152400b1b8f" );
		Assert.assertEquals( usuario.getEmail(), "user002@testing.com" );
		
		usuario = this.accountService.updateProfile( 1001L, "usuario editado", "a@a", null, null, null );
		
		Assert.assertEquals( usuario.getName(), "usuario editado" );
		Assert.assertEquals( usuario.getEmail(), "a@a" );
		

	}
}