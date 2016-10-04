package com.kelvearagao.brewer.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kelvearagao.brewer.model.Cerveja;
import com.kelvearagao.brewer.repository.Cervejas;

/**
 * Atenção por os seguintes jars no tomcat:
 * mchange-commons-java-0.2.11.jar
 * mysql-connector-java-5.1.35.jar
 * c3p0-0.9.5.2.jar
 * 
 * @author kelve
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = Cervejas.class, enableDefaultTransactions = false) // desabilita a trasação automática
@EnableTransactionManagement
public class JPAConfig {
	
	/**
	 * Disponibiliza o dataSource.
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		
		dataSourceLookup.setResourceRef(true);
		
		return dataSourceLookup.getDataSource("jdbc/brewerDB");
	}
	
	/**
	 * Configura o hibernate.
	 * 
	 * @return
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		
		adapter.setDatabase(Database.MYSQL); // tipo de banco
		adapter.setShowSql(true); // mostre a sql
		adapter.setGenerateDdl(false); // desativa a geração do db a partir do modelo
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect"); // dialeto
		
		return adapter;
	}
	
	/**
	 * Retorna o EntityManagerFactory.
	 * 
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan(Cerveja.class.getPackage().getName());
		factory.afterPropertiesSet();
		
		return factory.getObject();
	}
	
	/**
	 * Configur a transação.
	 * 
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		
		return transactionManager;
	}

}
