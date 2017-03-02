package com.lee.demo.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class SafeSingleton implements Serializable, Cloneable {

	private static final long serialVersionUID = -4147288492005226212L;

	private static SafeSingleton INSTANCE = new SafeSingleton();

	private SafeSingleton() {
		if (INSTANCE != null) {
			throw new IllegalStateException("Singleton instance Already created.");
		}
	}

	public static SafeSingleton getInstance() {
		return INSTANCE;
	}

	private Object readResolve() throws ObjectStreamException {
		return INSTANCE;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Singleton can't be cloned");
	}

	private static Class getClass(String className)
			throws ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null)
			classLoader = SafeSingleton.class.getClassLoader();
		// test
		System.out.println("Classloader = " + classLoader);
		return (classLoader.loadClass(className));
	}
}
