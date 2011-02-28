package ru.dehibernator.util;

public class Exceptions {
	public static <T> T rethrow(Throwable t) {
		if (t instanceof RuntimeException)
			throw (RuntimeException) t;
		else if (t instanceof Exception)
			throw new RethrownException((Exception) t);
		else if (t instanceof Error)
			throw (Error) t;
		else
			throw new Error(t);
	}
}
