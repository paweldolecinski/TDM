package com.tdm.server.security;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.provider.SocialAuthenticationService;

@SuppressWarnings("serial")
public class ConnectionFactoryLocatorPseudoProxy implements
	SocialAuthenticationServiceLocator, Serializable, BeanFactoryAware {
    private BeanFactory beanFactory;
    private final String targetBeanName;

    public ConnectionFactoryLocatorPseudoProxy(String targetBeanName) {
	this.targetBeanName = targetBeanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
	this.beanFactory = beanFactory;
    }

    @Override
    public <A> ConnectionFactory<A> getConnectionFactory(Class<A> apiType) {
	return getTargetBean().getConnectionFactory(apiType);
    }

    @Override
    public ConnectionFactory<?> getConnectionFactory(String providerId) {
	return getTargetBean().getConnectionFactory(providerId);
    }

    @Override
    public Set<String> registeredProviderIds() {
	return getTargetBean().registeredProviderIds();
    }

    private SocialAuthenticationServiceLocator getTargetBean() {
	return (SocialAuthenticationServiceLocator) beanFactory
		.getBean(targetBeanName);
    }

    @Override
    public SocialAuthenticationService<?> getAuthenticationService(
	    String providerId) {
	return getTargetBean().getAuthenticationService(providerId);
    }

    @Override
    public Set<String> registeredAuthenticationProviderIds() {
	return getTargetBean().registeredAuthenticationProviderIds();
    }
}
