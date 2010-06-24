package com.vicutu.json;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.Gson;
import com.vicutu.commons.test.LoggedBasicJUnit4ClassRunner;
import com.vicutu.commons.test.LoggedJUnit4BasicTests;
import com.vicutu.vo.Person;

@RunWith(LoggedBasicJUnit4ClassRunner.class)
public class GsonTestCase extends LoggedJUnit4BasicTests {

	@Test
	public void test_Gson() {
		Person p1 = new Person();
		p1.setId(Long.valueOf(1L));
		p1.setBirthday(new Date(System.currentTimeMillis()));
		p1.setName("Tom");
		p1.setSalary(200.36D);
		p1.setMemo("good man");
		debugger.println(p1.toString());
		Gson gson = new Gson();
		String jsonStr = gson.toJson(p1);
		debugger.println(jsonStr);

		Person p2 = gson.fromJson(jsonStr, Person.class);
		debugger.println(p2.toString());
	}
}
