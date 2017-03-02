package com.lee.demo.singleton;

import java.io.Serializable;

public class SerializableSingleton implements Serializable{

	private static final long serialVersionUID = 6789088557981297876L;

	private static final SerializableSingleton INSTANCE = new SerializableSingleton();

	private SerializableSingleton() {
	}

	public static SerializableSingleton getInstance() {
		return INSTANCE;
	}
}
