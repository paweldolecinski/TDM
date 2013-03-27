package org.springframework.orm.jdo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import javax.jdo.PersistenceManagerFactory;
import org.springframework.util.ClassUtils;

/**
 * Bugfix: https://code.google.com/p/googleappengine/issues/detail?id=1240
 * @author pdolecinski
 *
 */
public class GAETransactionAwarePersistenceManagerFactoryProxy extends
		TransactionAwarePersistenceManagerFactoryProxy {

	@Override
	public void setTargetPersistenceManagerFactory(
			PersistenceManagerFactory target) {
		try {
			// Set the "target" field. Which is private. So we need some dirty
			// reflection.
			Field targetField = TransactionAwarePersistenceManagerFactoryProxy.class
					.getDeclaredField("target");
			targetField.setAccessible(true);
			targetField.set(this, target);

			// hack: javax.naming.* classes not permitted by app-engine
			// iface[0]: javax.jdo.PersistenceManagerFactory
			// iface[1]: javax.naming.spi.ObjectFactory
			// iface[2]: javax.naming.Referenceable
			// keep just iface[0]
			Class<?>[] ifcs = ClassUtils.getAllInterfacesForClass(
					target.getClass(), target.getClass().getClassLoader());
			Class<?>[] _ifcs = new Class[] { ifcs[0] };

			// Reflection needed since
			// PersistenceManagerFactoryInvocationHandler is a private inner
			// class.
			@SuppressWarnings("unchecked")
			Class<? extends InvocationHandler> clazz = (Class<? extends InvocationHandler>) getClass()
					.getClassLoader()
					.loadClass(
							"org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy$PersistenceManagerFactoryInvocationHandler");
			Constructor<? extends InvocationHandler> constructor = clazz
					.getDeclaredConstructor(TransactionAwarePersistenceManagerFactoryProxy.class);
			constructor.setAccessible(true);

			InvocationHandler invocationHandler = constructor.newInstance(this);
			PersistenceManagerFactory proxy = (PersistenceManagerFactory) Proxy
					.newProxyInstance(target.getClass().getClassLoader(),
							_ifcs, invocationHandler);
			// Set the "proxy" field. Which is private. So we need some dirty
			// reflection.
			Field proxyField = TransactionAwarePersistenceManagerFactoryProxy.class
					.getDeclaredField("proxy");
			proxyField.setAccessible(true);
			proxyField.set(this, proxy);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error when trying to workaround issue with GAE and Spring 3.1. You might get this if you've upgraded Spring (since the reflection being done makes a lot of assumptions about the inner workings of Spring JDO).",
					e);
		}
	}
}
