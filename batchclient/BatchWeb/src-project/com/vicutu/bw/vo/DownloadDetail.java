package com.vicutu.bw.vo;

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
@Table(name = "bw_download_detail")
public class DownloadDetail implements Serializable, IReadable {

	/**
	 * 
	 */
	public static final int LAST_STATE_SEARCHED = 1;

	public static final int LAST_STATE_LOADED = 2;

	public static final int LAST_STATE_FAILED = 3;

	public static final int LAST_STATE_RELOADED = 4;
	
	public static final int LAST_STATE_IGNORED = 5;

	private static final long serialVersionUID = -7311886635868456532L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "real_url")
	private String realUrl;

	@Column(name = "real_path")
	private String realPath;

	@Column(name = "file_length")
	private long fileLength;

	@Column(name = "length_info")
	private String lenghtInfo;

	@Column(name = "last_state")
	private int lastState;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@Column(name = "memo")
	private String memo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRealUrl() {
		return realUrl;
	}

	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public long getFileLength() {
		return fileLength;
	}

	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public String getLenghtInfo() {
		return lenghtInfo;
	}

	public void setLenghtInfo(String lenghtInfo) {
		this.lenghtInfo = lenghtInfo;
	}

	public int getLastState() {
		return lastState;
	}

	public void setLastState(int lastState) {
		this.lastState = lastState;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
