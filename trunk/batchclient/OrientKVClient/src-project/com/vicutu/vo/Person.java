package com.vicutu.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Branch;
import org.dom4j.Element;

import com.vicutu.commons.lang.IReadable;
import com.vicutu.commons.xml.XmlUtils;

public class Person implements IReadable, Serializable {

	private static final long serialVersionUID = 2490926164490577847L;

	private Long id;

	private String name;

	private Date birthday;

	private double salary;

	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildElement(Branch parent) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> properties = (Map<String, Object>) BeanUtils.describe(this);
		Element rootElement = parent.addElement("Person");
		Set<Entry<String, Object>> entries = properties.entrySet();
		for (Entry<String, Object> entry : entries) {
			XmlUtils.addElement(rootElement, entry.getKey(), entry.getValue());
		}
	}

	@Override
	public String toString() {
		return XmlUtils.print(this);
	}
}
