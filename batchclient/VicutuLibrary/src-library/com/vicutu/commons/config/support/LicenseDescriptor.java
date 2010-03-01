package com.vicutu.commons.config.support;

import org.dom4j.Branch;
import org.dom4j.Element;

import com.vicutu.commons.lang.IReadable;
import com.vicutu.commons.xml.XmlUtils;

public class LicenseDescriptor implements IReadable
{
	private String id;

	private String product;

	private String type;

	private String company;

	private String version;

	private String expiresDate;

	private String creationDate;

	private String licenseSignature;

	private String classSignature;

	private String publicKey;

	private String library;

	private String privateKey;

	private String address;

	private String mac;

	public LicenseDescriptor()
	{
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(String creationDate)
	{
		this.creationDate = creationDate;
	}

	public String getExpiresDate()
	{
		return expiresDate;
	}

	public void setExpiresDate(String expiresDate)
	{
		this.expiresDate = expiresDate;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getProduct()
	{
		return product;
	}

	public void setProduct(String product)
	{
		this.product = product;
	}

	public String getLicenseSignature()
	{
		return licenseSignature;
	}

	public void setLicenseSignature(String licenseSignature)
	{
		this.licenseSignature = licenseSignature;
	}

	public String getClassSignature()
	{
		return classSignature;
	}

	public void setClassSignature(String classSignature)
	{
		this.classSignature = classSignature;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getPublicKey()
	{
		return publicKey;
	}

	public void setPublicKey(String publicKey)
	{
		this.publicKey = publicKey;
	}

	public String getLibrary()
	{
		return library;
	}

	public void setLibrary(String library)
	{
		this.library = library;
	}

	public String getPrivateKey()
	{
		return privateKey;
	}

	public void setPrivateKey(String privateKey)
	{
		this.privateKey = privateKey;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getMac()
	{
		return mac;
	}

	public void setMac(String mac)
	{
		this.mac = mac;
	}

	public byte[] getLicense() throws Exception
	{
		StringBuffer sb = new StringBuffer();
		sb.append(id == null ? "" : id);
		sb.append(company == null ? "" : company);
		sb.append(product == null ? "" : product);
		sb.append(version == null ? "" : version);
		sb.append(type == null ? "" : type);
		sb.append(mac == null ? "" : mac);
		sb.append(address == null ? "" : address);
		sb.append(creationDate == null ? "" : creationDate);
		sb.append(expiresDate == null ? "" : expiresDate);

		return sb.toString().getBytes("utf-8");
	}

	public void buildElement(Branch parent) throws Exception
	{
		Element rootElement = parent.addElement("license");
		XmlUtils.addElement(rootElement, "id", id);
		XmlUtils.addElement(rootElement, "product", product);
		XmlUtils.addElement(rootElement, "type", type);
		XmlUtils.addElement(rootElement, "company", company);
		XmlUtils.addElement(rootElement, "version", version);
		XmlUtils.addElement(rootElement, "mac", mac);
		XmlUtils.addElement(rootElement, "address", address);
		XmlUtils.addElement(rootElement, "expiresDate", expiresDate);
		XmlUtils.addElement(rootElement, "creationDate", creationDate);
		XmlUtils.addElement(rootElement, "licenseSignature", licenseSignature);
		XmlUtils.addElement(rootElement, "classSignature", classSignature);
		XmlUtils.addElement(rootElement, "publicKey", publicKey);
	}

	public String toString()
	{
		return XmlUtils.print(this, false);
	}
}