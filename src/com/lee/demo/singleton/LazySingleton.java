package com.lee.demo.singleton;

public class LazySingleton {

	private volatile static LazySingleton INSTANCE = null;

	private LazySingleton() {
	}

	public static synchronized LazySingleton getInstance() {
		if (INSTANCE == null)
			INSTANCE = new LazySingleton();
		return INSTANCE;
	}
}
