package br.com.berlanda.pousadapower.domain.entity;

import java.io.Serializable;

import org.hibernate.envers.RevisionType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import br.com.berlanda.pousadapower.application.security.ContextHolder;
import br.com.berlanda.pousadapower.domain.entity.account.Usuario;

/**
 * @author rodrigo.p.fraga@gmail.com
 * @since 06/12/2012
 * @version 1.0
 * @category Entity
 */
public class EntityTrackingRevisionListener implements org.hibernate.envers.EntityTrackingRevisionListener
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	public void newRevision( Object revisionEntity )
	{
		try
		{
			final Usuario usuario = ContextHolder.getAuthenticatedUser();
			( ( Revision<?, ?> ) revisionEntity ).setUserId( usuario.getId() );
		}
		catch ( AuthenticationCredentialsNotFoundException e )
		{
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.envers.EntityTrackingRevisionListener#entityChanged(java.lang.Class, java.lang.String, java.io.Serializable, org.hibernate.envers.RevisionType, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void entityChanged( Class entityClass, String entityName, Serializable entityId, RevisionType revisionType, Object revisionEntity )
	{
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
}