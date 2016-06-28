package com.digows.blank.domain.service;

import java.util.Calendar;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.digows.blank.domain.entity.account.Usuario;
import com.digows.blank.domain.entity.account.PermissaoUsuario;
import com.digows.blank.domain.repository.account.IUserRepository;

/**
 * 
 * @author rodrigo.p.fraga@gmail.com
 */
@Service
@RemoteProxy
@Transactional
public class AccountService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * Password encoder
	 */
	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	/**
	 * Hash generator for encryption
	 */
	@Autowired
	private SaltSource saltSource;

	/**
	 * 
	 */
	@Autowired
	private MessageSource messageSorce;

	// Repositories
	/**
	 * 
	 */
	@Autowired
	private IUserRepository userRepository;

	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param event
	 */
	@PreAuthorize("isAuthenticated() && #usuario.id == principal.id")
	public void updateLastUserLogin( Usuario usuario )
	{
		Assert.notNull( usuario );
		usuario = this.findUserById( usuario.getId() );
		usuario.setLastLogin( Calendar.getInstance() );
		this.userRepository.save( usuario );
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('" + PermissaoUsuario.ADMINISTRADOR_VALUE + "')")
	public Usuario insertUser( Usuario usuario )
	{
		Assert.notNull( usuario );

		usuario.setEnabled( true );

		usuario.setPassword( "temp123" );
		// encrypt password
		final String encodedPassword = this.passwordEncoder.encodePassword( usuario.getPassword(), this.saltSource.getSalt( usuario ) );
		usuario.setPassword( encodedPassword );

		return this.userRepository.save( usuario );
	}

	/**
	 * 
	 * @param userId
	 * @param name
	 * @param email
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('" + PermissaoUsuario.ADMINISTRADOR_VALUE + "')")
	public Usuario updateUserByAdmin( Long userId, String name, String email, PermissaoUsuario role, boolean status, String password, String confirmPassword )
	{
		Usuario usuario = this.userRepository.findOne( userId );

		usuario.setName( name );
		usuario.setEmail( email );
		usuario.setRole( role );
		usuario.setEnabled( status );

		if ( password != null )
		{
			Assert.notNull( confirmPassword, "Confirme a senha a ser alterada!" );
			if ( password.equals( confirmPassword ) )
			{
				final String encodedPassword = this.passwordEncoder.encodePassword( password, this.saltSource.getSalt( usuario ) );
				usuario.setPassword( encodedPassword );
			}
			else
			{
				throw new RuntimeException( "Confirme a senha a ser alterada" );
			}
		}
		return this.userRepository.save( usuario );
	}

	/**
	 * 
	 * @param userId
	 * @param name
	 * @param email
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	@PreAuthorize("#userId == principal.id")
	public Usuario updateProfile( Long userId, String name, String email, String currentPassword, String password, String confirmPassword )
	{
		Usuario usuario = this.userRepository.findOne( userId );
		
		usuario.setName( name );
		usuario.setEmail( email );

		if( password != null )
		{
			final String encodedOldPassword = this.passwordEncoder.encodePassword( currentPassword, this.saltSource.getSalt( usuario ) );

			if ( !encodedOldPassword.equals( usuario.getPassword() ) )
			{
				throw new IllegalArgumentException( "Senha inválida" );
			}

			if ( password.equals( confirmPassword ) )
			{
				final String encodedNewPassword = this.passwordEncoder.encodePassword( password, this.saltSource.getSalt( usuario ) );
				usuario.setPassword( encodedNewPassword );
			} else {
				throw new IllegalArgumentException( "Confirme a senha, as senhas estão incorretas" );
			}
		}

		
		return this.userRepository.saveAndFlush( usuario );

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Usuario findUserById( Long id )
	{
		final Usuario usuario = this.userRepository.findOne( id );
		Assert.notNull( usuario, this.messageSorce.getMessage( "repository.notFoundById", new Object[]
		{ id }, LocaleContextHolder.getLocale() ) );
		return usuario;
	}

	/**
	 * Ativa um usuário
	 * @param userId
	 */
	@PreAuthorize("hasAnyAuthority('"+PermissaoUsuario.ADMINISTRADOR_VALUE+"')")
	public void activateUser(Long userId){
		Assert.notNull( userId );
		this.userRepository.active( userId );
	}

	/**
	 * Desativa um usuário
	 * @param userId
	 */
	@PreAuthorize("hasAnyAuthority('"+PermissaoUsuario.ADMINISTRADOR_VALUE+"')")
	public void deactivateUser(Long userId){
		Assert.notNull( userId );
		this.userRepository.deactivate( userId );
	}
	
	/**
	 * 
	 * @param pageable
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Usuario> listUsersByFilters( String filter, PageRequest pageable )
	{
		return this.userRepository.listByFilters( filter, pageable );
	}
}