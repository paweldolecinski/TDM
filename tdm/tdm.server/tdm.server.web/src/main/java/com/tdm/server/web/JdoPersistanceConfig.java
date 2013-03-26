package com.tdm.server.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.orm.jdo.JdoTransactionManager;
import org.springframework.orm.jdo.LocalPersistenceManagerFactoryBean;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.tdm.server.persistance" }, excludeFilters = { @Filter(Configuration.class) })
@EnableTransactionManagement
public class JdoPersistanceConfig implements TransactionManagementConfigurer {

	@Bean
	LocalPersistenceManagerFactoryBean localPersistenceManagerFactoryBean() {
		LocalPersistenceManagerFactoryBean local = new LocalPersistenceManagerFactoryBean();
		local.setPersistenceManagerFactoryName("transactions-optional");
		return local;
	}

	@Bean
	public TransactionAwarePersistenceManagerFactoryProxy persistenceManagerFactory() {
		TransactionAwarePersistenceManagerFactoryProxy factory = new TransactionAwarePersistenceManagerFactoryProxy();

		factory.setTargetPersistenceManagerFactory(localPersistenceManagerFactoryBean()
				.getObject());
		factory.setAllowCreate(false);

		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JdoTransactionManager jdoTransactionManager = new JdoTransactionManager();
		jdoTransactionManager
				.setPersistenceManagerFactory(persistenceManagerFactory()
						.getObject());
		return jdoTransactionManager;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}
}
