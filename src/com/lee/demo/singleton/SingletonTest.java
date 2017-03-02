package com.lee.demo.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SingletonTest {

	private static final String OUTPUT = "output";

	public static void checkEagerSingleton() {
		EagerSingleton a = EagerSingleton.getInstance();
		EagerSingleton b = EagerSingleton.getInstance();

		assertEquals(a, b);
	}

	public static void checkLazySingleton() {
		LazySingleton a = LazySingleton.getInstance();
		LazySingleton b = LazySingleton.getInstance();

		assertEquals(a, b);
	}

	public static void checkLazySingletonWithDoubleCheck() {
		LazySingletonWithDoubleCheck a = LazySingletonWithDoubleCheck
				.getInstance();
		LazySingletonWithDoubleCheck b = LazySingletonWithDoubleCheck
				.getInstance();

		assertEquals(a, b);
	}

	public static void checkInnerClassSingleton() {
		InnerClassSingleton a = InnerClassSingleton.getInstance();
		InnerClassSingleton b = InnerClassSingleton.getInstance();

		assertEquals(a, b);
	}

	public static void checkSerialization() throws Exception {
		File file = new File(OUTPUT + File.separator
				+ "serializableSingleton.out");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				file));
		SerializableSingleton a = SerializableSingleton.getInstance();
		out.writeObject(a);
		out.close();

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		SerializableSingleton b = (SerializableSingleton) in.readObject();
		in.close();

		assertEquals(a, b);
	}

	public static void checkClone() throws Exception {
		ClonableSingleton a = ClonableSingleton.getInstance();
		ClonableSingleton b = (ClonableSingleton) a.clone();

		assertEquals(a, b);
	}

	public static void checkReflection() throws Exception {
		EagerSingleton a = EagerSingleton.getInstance();

		Constructor<EagerSingleton> cons = EagerSingleton.class
				.getDeclaredConstructor();
		cons.setAccessible(true);
		EagerSingleton b = (EagerSingleton) cons.newInstance();

		assertEquals(a, b);
	}

	public static void checkClassloader() throws Exception {
		String className = "com.lee.demo.singleton.EagerSingleton";
		// Class<?> clazz1 = Class.forName(className);
		ClassLoader classLoader1 = new MyClassloader();
		Class<?> clazz1 = classLoader1.loadClass(className);

		ClassLoader classLoader2 = new MyClassloader();
		Class<?> clazz2 = classLoader2.loadClass(className);

		System.out.println("classLoader1 = " + clazz1.getClassLoader());
		System.out.println("classLoader2 = " + clazz2.getClassLoader());

		Method getInstance1 = clazz1.getDeclaredMethod("getInstance");
		Method getInstance2 = clazz2.getDeclaredMethod("getInstance");
		Object a = getInstance1.invoke(null);
		Object b = getInstance2.invoke(null);

		assertEquals(a, b);
	}

	public static void checkSafeSingleton() throws Exception {

		System.out.println("Safe singleton check");
		SafeSingleton a = SafeSingleton.getInstance();

		// check serialization
		File file = new File(OUTPUT + File.separator + "safeSingleton.out");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				file));
		out.writeObject(a);
		out.close();

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		SafeSingleton b = (SafeSingleton) in.readObject();
		in.close();

		assertEquals(a, b);

		// check clone
		try {
			SafeSingleton c = (SafeSingleton) a.clone();
			assertEquals(a, c);
		} catch (CloneNotSupportedException e) {
			System.out.println(e.getMessage());
		}

		// check reflection
		// Constructor<SafeSingleton> cons = SafeSingleton.class
		// .getDeclaredConstructor();
		// cons.setAccessible(true);
		// SafeSingleton d = (SafeSingleton) cons.newInstance();
		//
		// assertEquals(a, d);
		System.out.println("context classloader = "
				+ Thread.currentThread().getContextClassLoader());

		// check classloader
		String className = "com.lee.demo.singleton.SafeSingleton";
		Class<?> clazz1 = Class.forName(className, true, new MyClassloader());
		// ClassLoader classLoader1 = new MyClassloader();
		// Class<?> clazz1 = classLoader1.loadClass(className);

		Class<?> clazz2 = Class.forName(className, true, new MyClassloader());
		// ClassLoader classLoader2 = new MyClassloader();
		// Class<?> clazz2 = classLoader2.loadClass(className);

		System.out.println("classLoader1 = " + clazz1.getClassLoader());
		System.out.println("classLoader2 = " + clazz2.getClassLoader());

		Method getInstance1 = clazz1.getDeclaredMethod("getInstance");
		Method getInstance2 = clazz2.getDeclaredMethod("getInstance");
		Object e = getInstance1.invoke(null);
		Object f = getInstance2.invoke(null);

		assertEquals(e, f);
	}

	public static void checkEnumSingleton() throws Exception {

		System.out.println("Enum singleton check");
		EnumSingleton a = EnumSingleton.INSTANCE;

		// check serialization
		File file = new File(OUTPUT + File.separator + "enumSingleton.out");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				file));
		out.writeObject(a);
		out.close();

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		EnumSingleton b = (EnumSingleton) in.readObject();
		in.close();

		assertEquals(a, b);

		// check clone
		// class Enum has a final clone method which throws
		// CloneNotSupportedException directly
		// so can't implement clone, no need to check clone

		// check reflection
		// Enum object can't be instantiated explicitly
		// so no need to check reflection through constructor

	}

	public static void assertEquals(Object a, Object b) {
		if (a != b) {
			System.err.println("a is not equal to b");
		} else {
			System.out.println("a is equal to b");
		}
	}

	public static void main(String args[]) throws Exception {
		// checkEagerSingleton();
		// checkLazySingleton();
		// checkLazySingletonWithDoubleCheck();
		// checkInnerClassSingleton();

		System.out.println("*******************");

		 checkSerialization();
		// checkClone();
		// checkReflection();
		// checkClassloader();

		System.out.println("*******************");
//		 checkSafeSingleton();
//		 checkEnumSingleton();
	}
}
