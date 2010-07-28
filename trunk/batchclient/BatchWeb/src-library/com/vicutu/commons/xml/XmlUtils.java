package com.vicutu.commons.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

import com.vicutu.commons.lang.DateUtils;
import com.vicutu.commons.lang.IReadable;
import com.vicutu.commons.lang.StringUtils;

public final class XmlUtils {
	private static final String ENCODING = "UTF-8";

	private XmlUtils() {
	}

	//------------------------------------------------------------------------//
	//--1--documnet
	//------------------------------------------------------------------------//
	public static Document createDocument() {
		return DocumentHelper.createDocument();
	}

	public static Document createDocument(String xml) throws DocumentException {
		return createDocument(new StringReader(xml));
	}

	public static Document createDocument(Reader reader) throws DocumentException {
		return createDocument(reader, false, null);
	}

	public static Document createDocument(Reader reader, boolean validate, String systemId) throws DocumentException {
		SAXReader saxreader = new SAXReader(validate);
		return saxreader.read(reader, systemId);
	}

	public static Document createDocument(InputStream in, boolean validate, String systemId) throws DocumentException {
		SAXReader saxreader = new SAXReader(validate);
		return saxreader.read(in, systemId);
	}

	public static Document createDocument(URL url, boolean validate) throws DocumentException {
		SAXReader saxreader = new SAXReader(validate);
		return saxreader.read(url);
	}

	public static Document createDocument(InputSource in, boolean validate) throws DocumentException {
		SAXReader saxreader = new SAXReader(validate);
		return saxreader.read(in);
	}

	public static Document createDocument(File file, boolean validate) throws DocumentException {
		SAXReader saxreader = new SAXReader(validate);
		return saxreader.read(file);
	}

	//------------------------------------------------------------------------//
	//--2--attribute
	//------------------------------------------------------------------------//
	public static void addAttribute(Element element, String name, Object value) {
		if (value != null) {
			addAttribute(element, name, value.toString());
		}
	}

	public static void addAttribute(Element element, String name, Date value) {
		if (value != null) {
			addAttribute(element, name, DateUtils.formatDate(((Date) value).getTime()));
		}
	}

	public static void addAttribute(Element element, String name, int value) {
		addAttribute(element, name, String.valueOf(value));
	}

	public static void addAttribute(Element element, String name, boolean value) {
		addAttribute(element, name, String.valueOf(value));
	}

	public static void addAttribute(Element element, String name, boolean value, boolean ignore) {
		if (ignore != value) {
			addAttribute(element, name, String.valueOf(value));
		}
	}

	public static void addAttribute(Element element, String name, double value) {
		addAttribute(element, name, String.valueOf(value));
	}

	public static void addAttribute(Element element, String name, long value) {
		addAttribute(element, name, String.valueOf(value));
	}

	public static void addAttribute(Element element, String name, String value) {
		if (element != null && name != null && value != null) {
			element.addAttribute(name, value);
		}
	}

	public static int getAttribute(Element element, String name, int defaultValue) {
		return StringUtils.getInteger(element.attributeValue(name), defaultValue);
	}

	public static long getAttribute(Element element, String name, long defaultValue) {
		return StringUtils.getLong(element.attributeValue(name), defaultValue);
	}

	public static String getAttribute(Element element, String name, String defaultValue) {
		return StringUtils.getString(element.attributeValue(name), defaultValue);
	}

	public static double getAttribute(Element element, String name, double defaultValue) {
		return StringUtils.getDouble(element.attributeValue(name), defaultValue);
	}

	public static boolean getAttribute(Element element, String name, boolean defaultValue) {
		return StringUtils.getBoolean(element.attributeValue(name), defaultValue);
	}

	//------------------------------------------------------------------------//
	//--3--element
	//------------------------------------------------------------------------//
	public static Element addElement(Branch parent, String name) {
		return parent.addElement(name);
	}

	public static Element addElement(Branch parent, String name, Object value) {
		return addElement(parent, name, value == null ? "" : value.toString());
	}

	public static Element addElement(Branch parent, String name, String value) {
		if (name != null && !name.equals("") && value != null) {
			Element element = parent.addElement(name);
			element.setText(value);
			return element;
		} else {
			return null;
		}
	}

	public static void setText(Branch parent, String value) {
		if (value != null) {
			parent.setText(value);
		}
	}

	public static void setCDATA(Element parent, Object value) {
		if (value != null) {
			parent.addCDATA(value.toString());
		}
	}

	public static void setCDATA(Element parent, byte[] value) {
		if (value != null) {
			parent.addCDATA(new String(value));
		}
	}

	public static void setCDATA(Element parent, String value) {
		if (value != null) {
			parent.addCDATA(value);
		}
	}

	public static void setCDATA(Branch parent, String name, String value) {
		if (value != null) {
			Element element = parent.addElement(name);
			element.addCDATA(value);
		}
	}

	public static void addObject(Branch parent, String name, Object object) throws Exception {
		if (object != null) {
			Element element = parent.addElement(name);
			addObject(element, object);
		}
	}

	public static void addObject(Branch parent, Object object) throws Exception {
		if (object != null) {
			if (object instanceof Collection) {
				Collection<?> list = (Collection<?>) object;
				for (Object item : list) {
					if (item instanceof IReadable) {
						((IReadable) item).buildElement(parent);
					} else {
						addElement(parent, "list-item", item);
					}
				}
			} else if (object instanceof Object[]) {
				Object[] array = (Object[]) object;
				for (int i = 0; i < array.length; i++) {
					Object item = array[i];
					if (item instanceof IReadable) {
						((IReadable) item).buildElement(parent);
					} else {
						addElement(parent, "array-item", item);
					}
				}
			} else if (object instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) object;

				Iterator<?> iter = map.keySet().iterator();
				while (iter.hasNext()) {
					Object key = iter.next();
					Object item = map.get(key);
					if (item instanceof IReadable) {
						((IReadable) item).buildElement(parent);
					} else {
						Element entry = addElement(parent, "map-item", item);
						addAttribute(entry, "name", key);
					}
				}
			} else if (object instanceof IReadable) {
				((IReadable) object).buildElement(parent);
			} else {
				setCDATA((Element) parent, object.toString());
			}
		}
	}

	//------------------------------------------------------------------------//
	//--4--print
	//------------------------------------------------------------------------//
	public static String print(IReadable node) {
		return print(node, ENCODING, true);
	}

	public static String print(IReadable node, boolean suppressDeclaration) {
		return print(node, ENCODING, suppressDeclaration);
	}

	public static String print(IReadable node, String encoding, boolean suppressDeclaration) {
		try {
			Document document = DocumentHelper.createDocument();
			node.buildElement(document);
			return print(document, encoding, suppressDeclaration);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String print(Document document) throws IOException {
		return print(document, ENCODING, true);
	}

	public static String print(Document document, String encoding, boolean suppressDeclaration) throws IOException {
		StringWriter writer = new StringWriter();

		try {
			print(document, writer, encoding, suppressDeclaration);
			return writer.toString();
		} finally {
			writer.close();
		}
	}

	public static void print(Document document, Writer out) throws IOException {
		print(document, out, ENCODING, true);
	}

	public static void print(Document document, Writer out, String encoding, boolean suppressDeclaration)
			throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding);
		format.setIndent("\t");
		format.setSuppressDeclaration(suppressDeclaration);
		print(document, out, format);
	}

	public static void print(Document document, Writer out, OutputFormat format) throws IOException {
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(document);
	}
}