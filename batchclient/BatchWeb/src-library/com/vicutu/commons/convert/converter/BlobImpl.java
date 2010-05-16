package com.vicutu.commons.convert.converter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;

import org.dom4j.Branch;
import org.dom4j.Element;

import com.vicutu.commons.lang.IReadable;
import com.vicutu.commons.xml.XmlUtils;

public class BlobImpl implements Blob, IReadable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5409513951398458874L;
	private byte[] buffer;

	public BlobImpl(byte[] buffer) {
		this.buffer = buffer;
	}

	public BlobImpl(String buffer) {
		this.buffer = buffer.getBytes();
	}

	//------------------------------------------------------------------------//
	//--implement
	//------------------------------------------------------------------------//
	public InputStream getBinaryStream() throws SQLException {
		return new ByteArrayInputStream(buffer);
	}

	public byte[] getBytes(long pos, int length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public long length() throws SQLException {
		return buffer.length;
	}

	public long position(byte[] pattern, long start) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public long position(Blob pattern, long start) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public OutputStream setBinaryStream(long pos) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public int setBytes(long pos, byte[] bytes) throws SQLException {
		return setBytes(pos, bytes, 0, bytes.length);
	}

	public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void truncate(long len) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void buildElement(Branch parent) throws Exception {
		Element rootElement = parent.addElement("blob");
		XmlUtils.setCDATA(rootElement, new String(buffer));
	}

	public String toString() {
		return XmlUtils.print(this);
	}

	@Override
	public void free() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream getBinaryStream(long pos, long length) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}