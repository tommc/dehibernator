package ru.dehibernator.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
	public static List<String> getProperties(Object object) {
		List<String> properties = new ArrayList<String>();
		Class<? extends Object> xClass = object.getClass();
		for (Method method : xClass.getMethods()) {
			if (isGetter(method)) {
				String property = method.getName().substring(3);
				property = StringUtil.lcFirst(property);
				properties.add(property);
			}
		}
		return properties;
	}

	private static boolean isGetter(Method method) {
		String name = method.getName();
		if (!name.startsWith("get"))
			return false;
		if (name.length() == 3)
			return false;
		if (name.equals("getClass"))
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Object object, String property) {
		try {
			Class<? extends Object> xClass = object.getClass();
			String methodName = "get" + StringUtil.ucFirst(property);
			Method method = xClass.getMethod(methodName);
			return (T) method.invoke(object);
		}
		catch (Exception e) {
			return (T) Exceptions.rethrow(e);
		}
	}

	public static void set(Object object, String property, Object value) {
		try {
			Class<? extends Object> xClass = object.getClass();
			String methodName = "set" + StringUtil.ucFirst(property);
			Method method = getMethod(xClass, methodName);
			if (method == null)
				throw new NullPointerException(object.getClass() + "; " + object + "; " + methodName);
			method.invoke(object, value);
		}
		catch (Exception e) {
			Exceptions.rethrow(e);
		}
	}

	public static void setIfPossible(Object object, String property, Object value) {
		try {
			Class<? extends Object> xClass = object.getClass();
			String methodName = "set" + StringUtil.ucFirst(property);
			Method method = getMethod(xClass, methodName);
			if (method == null)
				return;
			method.invoke(object, value);
		}
		catch (Exception e) {
			Exceptions.rethrow(e);
		}
	}

	private static Method getMethod(Class<?> xClass, String name) {
		Method[] methods = xClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(name))
				return method;
		}
		return null;
	}
}
