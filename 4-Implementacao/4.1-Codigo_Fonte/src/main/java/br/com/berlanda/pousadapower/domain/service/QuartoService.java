/**
 * 
 */
package br.com.berlanda.pousadapower.domain.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.jcr.RepositoryException;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.berlanda.pousadapower.domain.entity.quarto.Quarto;
import br.com.berlanda.pousadapower.domain.repository.IQuartoRepository;
import br.com.eits.common.infrastructure.jcr.MetaFile;
import br.com.eits.common.infrastructure.jcr.modeshape.MetaFileRepository;


/**
 * @author BERLANDA
 *
 */
@Service
@RemoteProxy
@Transactional
public class QuartoService
{
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 */
	@Autowired
	private IQuartoRepository quartoRepository;
	
	@Autowired
	private MetaFileRepository metaFileRepository;
	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/
	
	
	/**
	 * 
	 * @param filter
	 * @param page
	 * @return
	 */
	public Page<Quarto> listQuartosByFilters( String filter, PageRequest page )
	{
		return this.quartoRepository.listByFilters( filter, page );
	}
	
	
	/**
	 * 
	 * @param quartoId
	 * @return
	 */
	public Quarto findQuartoById ( Long quartoId )
	{
		Assert.notNull( quartoId );
		
		Quarto quarto = this.quartoRepository.findOne( quartoId );
		
		Assert.notNull( quarto, "Quarto não encontrado" );
		
		return quarto;
	}
	
	/**
	 * 
	 * @param quartoId
	 * @return
	 */
	public FileTransfer photoRender( Long quartoId )
	{
		MetaFile metaFile= null;
		
		Quarto quarto = this.quartoRepository.findOne( quartoId );
		
		try
		{
			metaFile = this.metaFileRepository.findByPath( quarto.getPath() , true );
		}
		catch ( RepositoryException e )
		{}

		if( metaFile != null )
		{
			return new FileTransfer(metaFile.getName(), metaFile.getContentType(), metaFile.getInputStream());
		}

		return null;

	}
	

	/**
	 * 
	 * @param quarto
	 * @param fileTransfer
	 * @return
	 */
	public Quarto insertQuarto ( Quarto quarto, FileTransfer fileTransfer )
	{
		Assert.notNull( quarto );
		
		Assert.isNull( quarto.getId() );
		
		quarto = this.quartoRepository.save( quarto );
		quarto.setAtivo( true );
		
		MetaFile metaFile = null;
		if ( fileTransfer != null ) 
		{
			quarto.isValidFile( fileTransfer );
			String folder = String.format( Quarto.QUARTO_FILE_FOLDER, quarto.getId() );
			
			try
			{
				metaFile = this.insertMetaFile( fileTransfer, folder, quarto.getId().toString() );
			}
			catch ( IOException | RepositoryException e )
			{
				e.printStackTrace();
			}
		}
		
		return quarto;
		
	}
	
	/**
	 * 
	 * @param fileTransfer
	 * @param folder
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws RepositoryException
	 */
	public MetaFile insertMetaFile ( FileTransfer fileTransfer, String folder, String id ) throws IOException, RepositoryException
	{
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		IOUtils.copy( fileTransfer.getInputStream(), byteArrayOutputStream );
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream( byteArrayOutputStream.toByteArray() );
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType( fileTransfer.getMimeType() );
		metaFile.setFolder( folder );
		metaFile.setName( fileTransfer.getFilename() );
		metaFile.setId( id );
		metaFile.setInputStream( byteArrayInputStream );
		return this.metaFileRepository.insert( metaFile );

	}
	
	/**
	 * 
	 * @param equipment
	 * @param photo
	 * @return
	 */
	public Quarto updateQuarto ( Quarto quarto, FileTransfer fileTransfer )
	{
		Assert.notNull( quarto );
		
		Assert.notNull( quarto.getId() );
		
		quarto = this.quartoRepository.save( quarto );
		this.quartoRepository.flush();
		
		if ( fileTransfer != null ) 
		{
			quarto.isValidFile( fileTransfer );
			String folder = String.format( Quarto.QUARTO_FILE_FOLDER, quarto.getId() );
			
			try
			{
				this.metaFileRepository.removeByPath( quarto.getPath() );
			}
			catch ( RepositoryException e )
			{
				e.printStackTrace();
			}
			
			try
			{
				this.insertMetaFile( fileTransfer, folder, quarto.getId().toString() );
			}
			catch ( IOException e )
			{
				e.printStackTrace();
			}
			catch ( RepositoryException e )
			{
				e.printStackTrace();
			}
		}
		
		return quarto;
	}
	
	/**
	 * 
	 * @param equipmentId
	 */
//	@PreAuthorize("hasAnyAuthority('"+UserRole.ADMINISTRATOR_VALUE+"')")
	public void removeQuarto ( long quartoId )
	{
		Quarto quarto = this.findQuartoById( quartoId );
		
		this.quartoRepository.delete( quartoId );
		
		try
		{
			this.metaFileRepository.removeByPath( quarto.getPath() );
		}
		catch ( RepositoryException e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param equipmentId
	 */
	public void removeEquipmentFile ( long quartoId )
	{
		Quarto quarto = this.findQuartoById( quartoId );
		
		try
		{
			this.metaFileRepository.removeByPath( quarto.getPath() );
		}
		catch ( RepositoryException e )
		{
			e.printStackTrace();
		}
	}
}
