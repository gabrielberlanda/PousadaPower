package br.com.berlanda.pousadapower.application.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.berlanda.pousadapower.application.security.ContextHolder;
import br.com.berlanda.pousadapower.domain.entity.account.Usuario;

/**
 * 
 * @author rodrigo
 */
@Controller
public class NavigationController
{
	/*-------------------------------------------------------------------
	 * 		 					CONTROLLERS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView home()
	{
		return new ModelAndView( "modules/home/views/index" );
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/authentication")
	public ModelAndView authentication()
	{
		return new ModelAndView( "modules/authentication/views/index" );
	}
}
