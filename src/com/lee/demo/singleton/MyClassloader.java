package com.lee.demo.singleton;

import java.io.IOException;
import java.io.InputStream;

public class MyClassloader extends ClassLoader {
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
		InputStream in = getClass().getResourceAsStream(fileName);
		if (in == null) {
			return super.loadClass(name);
		}
		byte[] b = null;
		try {
			b = new byte[in.available()];
			in.read(b);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return defineClass(name, b, 0, b.length);
	}
}
