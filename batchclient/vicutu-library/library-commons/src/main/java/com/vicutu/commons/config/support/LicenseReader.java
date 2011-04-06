package com.vicutu.commons.config.support;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.digester.Digester;

public class LicenseReader {
	private LicenseReader() {
	}

	public static LicenseDescriptor read(String licenseXml) throws Exception {
		return read(new StringReader(licenseXml));
	}

	public static LicenseDescriptor read(File licenseFile) throws Exception {
		FileReader reader = new FileReader(licenseFile);

		try {
			return read(reader);
		} finally {
			reader.close();
		}
	}

	private static LicenseDescriptor read(Reader reader) throws Exception {
		Digester digester = new Digester();
		digester.setUseContextClassLoader(true);

		digester.addObjectCreate("license", LicenseDescriptor.class);
		digester.addSetProperties("license");

		digester.addCallMethod("license/id", "setId", 1);
		digester.addCallParam("license/id", 0);

		digester.addCallMethod("license/product", "setProduct", 1);
		digester.addCallParam("license/product", 0);

		digester.addCallMethod("license/type", "setType", 1);
		digester.addCallParam("license/type", 0);

		digester.addCallMethod("license/company", "setCompany", 1);
		digester.addCallParam("license/company", 0);

		digester.addCallMethod("license/version", "setVersion", 1);
		digester.addCallParam("license/version", 0);

		digester.addCallMethod("license/mac", "setMac", 1);
		digester.addCallParam("license/mac", 0);

		digester.addCallMethod("license/address", "setAddress", 1);
		digester.addCallParam("license/address", 0);

		digester.addCallMethod("license/expiresDate", "setExpiresDate", 1);
		digester.addCallParam("license/expiresDate", 0);

		digester.addCallMethod("license/creationDate", "setCreationDate", 1);
		digester.addCallParam("license/creationDate", 0);

		digester.addCallMethod("license/licenseSignature", "setLicenseSignature", 1);
		digester.addCallParam("license/licenseSignature", 0);

		digester.addCallMethod("license/classSignature", "setClassSignature", 1);
		digester.addCallParam("license/classSignature", 0);

		digester.addCallMethod("license/publicKey", "setPublicKey", 1);
		digester.addCallParam("license/publicKey", 0);

		digester.addCallMethod("license/privateKey", "setPrivateKey", 1);
		digester.addCallParam("license/privateKey", 0);

		digester.addCallMethod("license/library", "setLibrary", 1);
		digester.addCallParam("license/library", 0);

		return (LicenseDescriptor) digester.parse(reader);
	}
}