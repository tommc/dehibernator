package ru.dehibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.collection.PersistentList;

public class MockPersistentList extends PersistentList {
	private static final long serialVersionUID = -544351173166571231L;

	private List<Object> list = new ArrayList<Object>();

	public MockPersistentList() {
		setInitialized();
	}

	@Override
	public boolean add(Object object) {
		return list.add(object);
	}

	@Override
	public String toString() {
		return list.toString();
	}

	@Override
	public boolean equals(Object other) {
		return list.equals(other);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ListIterator listIterator() {
		return list.listIterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Iterator iterator() {
		return list.iterator();
	}
}
