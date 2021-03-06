package br.com.berlanda.pousadapower.domain.repository;
import java.util.concurrent.Future;

import br.com.berlanda.pousadapower.domain.entity.account.Usuario;
 
/**
 * Interface para o envio de e-mails
 *
 * @author rodrigo.p.fraga@gmail.com
 * @since 02/10/2014
 * @version 1.0
 */
public interface IAccountMailRepository
{
    /*-------------------------------------------------------------------
     *                          BEHAVIORS
     *-------------------------------------------------------------------*/
    /**
     * @param usuario
     */
    public Future<Void> sendNewUserAccount( Usuario usuario );
}