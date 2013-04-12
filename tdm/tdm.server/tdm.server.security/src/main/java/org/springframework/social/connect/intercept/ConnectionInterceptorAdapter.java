package org.springframework.social.connect.intercept;

import java.util.Collection;

import org.springframework.social.connect.Connection;

public class ConnectionInterceptorAdapter<S> implements
		ConnectionInterceptor<S> {

	@Override
	public void afterCreate(String userId, Connection<S> connection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeCreate(String userId, Connection<S> connection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterRemove(String userId,
			Collection<? extends Connection<S>> connections) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeRemove(String userId,
			Collection<? extends Connection<S>> connections) {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterUpdate(String userId, Connection<S> connection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeUpdate(String userId, Connection<S> connection) {
		// TODO Auto-generated method stub
	}

}
