package ru.dehibernator;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class DehibernateAdvice {
	@AfterReturning(pointcut = "@annotation(ru.dehibernate.Dehibernate)", returning = "value")
	public void unhibernate(Object value) {
		Dehibernator dehibernator = new Dehibernator();
		dehibernator.clean(value);
	}
}
