package com.vicutu.kv;

import java.net.URI;
import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import com.vicutu.commons.test.LoggedJUnit4SpringContextTests;
import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;
import com.vicutu.vo.Person;

@ContextConfiguration(locations = { "ctx-kv-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class ByteArrayRESTfulKVClientTestCase extends LoggedJUnit4SpringContextTests {

	public static final String BASE_URI = "http://localhost:2431/entry/demo/space/";

	@Autowired
	@Qualifier("jsonRESTfulKVClient")
	private RESTfulKVClient kvClientService;

	@Test
	public void test_post() throws Throwable {

		Person p1 = new Person();
		p1.setId(Long.valueOf(3L));
		p1.setBirthday(new Date(System.currentTimeMillis()));
		p1.setName("Tom");
		p1.setSalary(200.36D);
		p1.setMemo("good man");
		debugger.println(p1.toString());

		URI uri = new URI(BASE_URI + p1.getId());
		debugger.println(kvClientService.post(uri, p1));
	}

	@Test
	public void test_batchPost() throws Throwable {
		Person p1 = new Person();

		p1.setBirthday(new Date(System.currentTimeMillis()));
		p1.setName("Tom");
		p1.setSalary(200.36D);
		p1.setMemo("good man");
		for (int i = 0; i < 10; i++) {
			p1.setId(Long.valueOf(i));
			URI uri = new URI(BASE_URI + i);
			debugger.println(kvClientService.post(uri, p1));
		}
	}

	@Test
	public void test_get() throws Throwable {
		URI uri = new URI(BASE_URI + Long.valueOf(1L));
		Person p1 = kvClientService.get(uri, Person.class);
		debugger.println(p1);
	}

	@After
	public void cleanup() throws Throwable {
		debugger.println("clean up...");
		for (int i = 0; i < 100; i++) {
			debugger.println(kvClientService.delete(new URI(BASE_URI + i)));
		}
	}
}
