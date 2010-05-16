package com.vicutu.commons.convert.converter;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

import org.dom4j.Branch;
import org.dom4j.Element;

import com.vicutu.commons.lang.IReadable;
import com.vicutu.commons.xml.XmlUtils;

public class ClobImpl implements Clob, IReadable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4497642530921898492L;
	private char[] buffer;

	public ClobImpl(char[] buffer) {
		this.buffer = buffer;
	}

	public ClobImpl(String buffer) {
		this.buffer = buffer.toCharArray();
	}

	//------------------------------------------------------------------------//
	//--implement
	//------------------------------------------------------------------------//
	public InputStream getAsciiStream() throws SQLException {
		return new ByteArrayInputStream(new String(buffer).getBytes());
	}

	public Reader getCharacterStream() throws SQLException {
		return new CharArrayReader(buffer);
	}

	public String getSubString(long pos, int length) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public long length() throws SQLException {
		return buffer.length;
	}

	public long position(String searchstr, long start) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public long position(Clob searchstr, long start) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public OutputStream setAsciiStream(long pos) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public Writer setCharacterStream(long pos) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public int setString(long pos, String str) throws SQLException {
		return setString(pos, str, 0, str.length());
	}

	public int setString(long pos, String str, int offset, int len) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void truncate(long len) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public void buildElement(Branch parent) throws Exception {
		Element rootElement = parent.addElement("clob");
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
	public Reader getCharacterStream(long pos, long length) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}