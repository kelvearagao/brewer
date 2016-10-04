package com.kelvearagao.brewer.config.init;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.kelvearagao.brewer.config.JPAConfig;
import com.kelvearagao.brewer.config.ServiceConfig;
import com.kelvearagao.brewer.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/**
	 * Configura antes do getServletConfigClasses.
	 * O que for dos serviços pra tras, fica aqui!
	 * 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class };
	}

	/**
	 * Retorna a classe de configuração do spring.
	 * O que for de web fica aqui (dos controllers pra frente)!
	 * 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	/**
	 * Padrão de url aceito.
	 * 
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	/**
	 *  Força o encoding UTF-8.
	 *  Geralmente isso é necessário em SOs windows.
	 *  
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		return new Filter[] { characterEncodingFilter };
	}

}
