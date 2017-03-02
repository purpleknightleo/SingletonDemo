package com.lee.demo.singleton;

public class ClonableSingleton implements Cloneable{

	private static final ClonableSingleton INSTANCE = new ClonableSingleton();

	private ClonableSingleton() {
	}

	public static ClonableSingleton getInstance() {
		return INSTANCE;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
