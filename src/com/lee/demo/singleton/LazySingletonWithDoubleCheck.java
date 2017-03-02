package com.lee.demo.singleton;

public class LazySingletonWithDoubleCheck {

	private volatile static LazySingletonWithDoubleCheck INSTANCE = null;

	private LazySingletonWithDoubleCheck() {
	}

	public static LazySingletonWithDoubleCheck getInstance() {
		if (INSTANCE == null) {
			synchronized (LazySingletonWithDoubleCheck.class) {
				if (INSTANCE == null)
					INSTANCE = new LazySingletonWithDoubleCheck();
			}
		}
		return INSTANCE;
	}
}
