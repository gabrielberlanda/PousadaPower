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

	//Repositories
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
	@PreAuthorize("isAuthenticated() && #user.id == principal.id")
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
	@PreAuthorize("hasAnyAuthority('"+PermissaoUsuario.ADMINISTRATOR_VALUE+"','"+PermissaoUsuario.MANAGER_VALUE+"')")
	public Usuario insertUser( Usuario usuario )
	{
		Assert.notNull( usuario );

		usuario.setEnabled( true );
		// encrypt password
		final String encodedPassword = this.passwordEncoder.encodePassword( usuario.getPassword(), this.saltSource.getSalt( usuario ) );
		usuario.setPassword( encodedPassword );

		return this.userRepository.save( usuario );
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Usuario findUserById( Long id )
	{
		final Usuario usuario = this.userRepository.findOne( id );
		Assert.notNull( usuario, this.messageSorce.getMessage("repository.notFoundById", new Object[]{id}, LocaleContextHolder.getLocale()) );
		return usuario;
	}
	
	/**
	 * 
	 * @param pageable
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<Usuario> listUsersByFilters( String filter, PageRequest pageable )
	{
		return this.userRepository.listByFilters( filter, pageable );
	}
}