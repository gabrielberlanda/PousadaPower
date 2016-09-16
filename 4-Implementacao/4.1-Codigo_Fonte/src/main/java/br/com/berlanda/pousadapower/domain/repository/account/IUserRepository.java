package br.com.berlanda.pousadapower.domain.repository.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.berlanda.pousadapower.domain.entity.account.Usuario;

/**
 * 
 * @author rodrigo.p.fraga@gmail.com 
 * @since 22/04/2014
 * @version 1.0
 * @category Repository
 */
public interface IUserRepository extends JpaRepository<Usuario, Long>
{
	/**
	 * @param username
	 * @return
	 */
	public Usuario findByEmail(String email);
	
	/**
	 * @param filter
	 * @param pageable
	 * @return
	 */
	@Query(value="SELECT new Usuario(usuario.id, usuario.name, usuario.email, usuario.enabled, usuario.role) " +
				   "FROM Usuario usuario " +
				  "WHERE ( FILTER(usuario.id, :filter) = TRUE "
				  	 + "OR FILTER(usuario.name, :filter) = TRUE "
				  	 + "OR FILTER(usuario.email, :filter) = TRUE )" )
	public Page<Usuario> listByFilters( @Param("filter") String filter, Pageable pageable );
	
	/**
	 * Ativa um usuário
	 * @param id
	 */
	@Modifying
	@Query("update Usuario u set u.enabled = true, u.updated = now() where u.id= :id")
	public void active ( @Param("id") Long id );
	
	/**
	 * Desativa um usuário
	 * @param id
	 */
	@Modifying
	@Query("update Usuario u set u.enabled = false, u.updated = now() where u.id =:id")
	public void deactivate ( @Param("id") Long id );
}
