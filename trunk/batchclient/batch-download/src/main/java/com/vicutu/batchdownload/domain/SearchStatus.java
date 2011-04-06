package com.vicutu.batchdownload.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Branch;
import org.dom4j.Element;

import com.vicutu.commons.lang.IReadable;
import com.vicutu.commons.xml.XmlUtils;

@Entity
@Table(name = "bw_search_status")
public class SearchStatus implements Serializable, IReadable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6253033249028505058L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "access_name")
	private String accessName;

	@Column(name = "last_search_url")
	private String lastSearchUrl;

	@Column(name = "last_search_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastSearchTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	public String getLastSearchUrl() {
		return lastSearchUrl;
	}

	public void setLastSearchUrl(String lastSearchUrl) {
		this.lastSearchUrl = lastSearchUrl;
	}

	public Date getLastSearchTime() {
		return lastSearchTime;
	}

	public void setLastSearchTime(Date lastSearchTime) {
		this.lastSearchTime = lastSearchTime;
	}

	@Override
	public String toString() {
		return XmlUtils.print(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildElement(Branch parent) throws Exception {
		Map<String, Object> properties = (Map<String, Object>) BeanUtils.describe(this);
		Element rootElement = parent.addElement("AccessDetail");
		Set<Entry<String, Object>> entries = properties.entrySet();
		for (Entry<String, Object> entry : entries) {
			XmlUtils.addElement(rootElement, entry.getKey(), entry.getValue());
		}
	}
}
