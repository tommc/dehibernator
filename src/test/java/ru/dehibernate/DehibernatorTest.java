package ru.dehibernate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.collection.internal.PersistentList;
import org.junit.Test;

import ru.dehibernate.Company.Size;
import ru.dehibernator.Dehibernator;

public class DehibernatorTest {
	@Test
	public void clean_baseProperties() {
		Company company = new Company();
		company.setName("IIS");
		company.setSize(Size.SMALL);

		Dehibernator util = new Dehibernator();
		Company xCompany = util.clean(company);

		assertThat(xCompany.getName(), is("IIS"));
		assertThat(xCompany.getNullString(), is(nullValue()));
		assertThat(xCompany.getSize(), is(Size.SMALL));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void clean_list() {
		Company company = new Company();

		PersistentList contacts = new MockPersistentList();
		company.setContacts(contacts);

		Contact contact1 = new Contact();
		contacts.add(contact1);

		Contact contact2 = new Contact();
		contacts.add(contact2);

		Dehibernator util = new Dehibernator();
		Company xCompany = util.clean(company);

		List<Contact> xContacts = xCompany.getContacts();
		assertThat((Class<ArrayList>) xContacts.getClass(), is(equalTo(ArrayList.class)));
		assertThat(xContacts, is((List<Contact>) contacts));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void clean_nestedLists() {
		Company company = new Company();

		PersistentList contacts = new MockPersistentList();

		Contact contact1 = new Contact();
		contacts.add(contact1);

		Contact contact2 = new Contact();

		List<String> phones = new MockPersistentList();
		phones.add("111");
		phones.add("222");
		contact2.setPhones(phones);

		contacts.add(contact2);

		company.setContacts(contacts);

		Dehibernator util = new Dehibernator();
		Company xCompany = util.clean(company);

		List<Contact> xContacts = xCompany.getContacts();
		assertThat((Class<ArrayList>) xContacts.getClass(), is(equalTo(ArrayList.class)));
		assertThat(xContacts, is((List<Contact>) contacts));

		Contact xContact1 = xContacts.get(0);
		assertThat(xContact1.getPhones(), is(nullValue()));

		Contact xContact2 = xContacts.get(1);

		List<String> xPhones = xContact2.getPhones();
		assertThat((Class<ArrayList>) xPhones.getClass(), is(equalTo(ArrayList.class)));
		assertThat(xPhones, is(phones));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void clean_cyclicReference() {
		Company company = new Company();

		PersistentList contacts = new MockPersistentList();

		Contact contact = new Contact();
		contact.setWorksIn(company);
		contacts.add(contact);

		company.setContacts(contacts);

		Dehibernator util = new Dehibernator();
		Company xCompany = util.clean(company);

		List<Contact> xContacts = xCompany.getContacts();
		assertThat((Class<ArrayList>) xContacts.getClass(), is(equalTo(ArrayList.class)));
		assertThat(xContacts, is((List<Contact>) contacts));

		Contact xContact = xContacts.get(0);
		assertThat(xContact.getWorksIn(), is(company));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void repeatableProxy() {
		List<Object> list = new ArrayList<Object>();
		PersistentList persistentList = new MockPersistentList();
		list.add(persistentList);
		list.add(persistentList);

		Dehibernator util = new Dehibernator();
		List<Object> cleanedList = util.clean(list);
		List list0 = (List) cleanedList.get(0);
		assertThat((Class<ArrayList>) list0.getClass(), is(equalTo(ArrayList.class)));
		List<?> list1 = (List) cleanedList.get(1);
		assertThat((Class<ArrayList>) list1.getClass(), is(equalTo(ArrayList.class)));
	}
}
