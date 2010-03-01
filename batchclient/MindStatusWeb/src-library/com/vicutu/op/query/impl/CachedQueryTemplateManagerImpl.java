package com.vicutu.op.query.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.lang.PathUtils;
import com.vicutu.commons.xml.XmlUtils;
import com.vicutu.op.query.QueryTemplate;
import com.vicutu.op.query.QueryTemplateManager;

public class CachedQueryTemplateManagerImpl implements QueryTemplateManager {
	
	private static final String FILE_PATTERN="[\\S]*.xml";
	
	private static final String QUERY_PATH="resource/query";
	
	Map<String,QueryTemplate> map=new HashMap<String,QueryTemplate>();
	
	public CachedQueryTemplateManagerImpl(){
		String queryPath=PathUtils.getSystemPath()+QUERY_PATH;
		File queryDir=new File(queryPath);
		File[] queryFiles=FileUtils.filterFiles(queryDir, FILE_PATTERN);
		
		try {
			for(int i=0;i<queryFiles.length;i++){
				
				String fileName=queryFiles[i].getName().toLowerCase();
				String defaultQueryName=fileName.substring(0,fileName.indexOf(".xml"));
				Document doc=XmlUtils.createDocument(queryFiles[i], false);
				QueryTemplate qt=new QueryTemplateImpl(doc.getRootElement(),defaultQueryName);
				map.put(qt.getName(), qt);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	public QueryTemplate getQueryTemplate(String name) {
		return map.get(name);
	}
}
