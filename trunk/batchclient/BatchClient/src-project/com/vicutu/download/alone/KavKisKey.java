package com.vicutu.download.alone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.vicutu.commons.lang.FileUtils;

public class KavKisKey
{

	private static final String ROOT_URL = "http://www.kavkiskey.com/";

	private static final String SAVE_PATH = "F:/Downloads/bt/";

	private static final String[] PREFIXS = new String[] { "KIS8", "KAV7" };

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{

		DefaultHttpClient httpclient = new DefaultHttpClient();
		initClient(httpclient);

		String htmlStr = getHtmlStr(httpclient, ROOT_URL);
		List<String> keyNames = getKeyName(htmlStr);
		download(httpclient, keyNames);

	}

	private static void initClient(DefaultHttpClient httpclient)
	{
		//		httpclient.getCredentialsProvider().setCredentials(new AuthScope("dl-proxyall.neusoft.com", 8080), new UsernamePasswordCredentials("dipengfei", "VicutU19*@"));
		//		HttpHost proxyHttpHost = new HttpHost("dl-proxyall.neusoft.com", 8080);
		//		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxyHttpHost);
	}

	private static String getHtmlStr(DefaultHttpClient httpclient, String linkUrl) throws Exception
	{
		HttpGet httpget = new HttpGet(linkUrl);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null)
		{
			return EntityUtils.toString(entity, "utf-8");
		}
		return null;
	}

	private static List<String> getKeyName(String htmlStr) throws Exception
	{
		List<String> keyNames = new ArrayList<String>();
		Set<String> prefixSet = new HashSet<String>();
		for (int j = 0; j < PREFIXS.length; j++)
		{
			prefixSet.add(PREFIXS[j]);
		}

		Parser parser = new Parser();
		parser.setInputHTML(htmlStr);
		NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("a"));
		for (int i = 0; i < nla.size(); i++)
		{
			Node node = nla.elementAt(i);

			if (node instanceof LinkTag)
			{
				LinkTag lt = (LinkTag) node;
				String linkUrl0 = lt.getLink();
				if (linkUrl0.startsWith(("/DownLoad.Asp?File=")))
				{
					String keyName = StringUtils.substringAfterLast(linkUrl0, "File=");
					if (prefixSet.contains(keyName.substring(0, 4)))
					{
						keyNames.add(keyName);
					}
				}
			}
		}
		return keyNames;
	}

	private static void download(DefaultHttpClient httpclient, List<String> imageUrls)
	{
		FileOutputStream fos = null;
		HttpGet httpget = null;
		HttpEntity entity = null;
		HttpResponse response = null;
		for (int i = 0; i < imageUrls.size(); i++)
		{
			String fileName = imageUrls.get(i);
			System.out.println("Key Name : " + fileName);
			String url = "http://www.kavkiskey.com/DownLoad.Asp?File=" + fileName + "&Type=Down";
			try
			{
				fos = FileUtils.openOutputStream(new File(SAVE_PATH, fileName + ".key"));
				httpget = new HttpGet(url);
				response = httpclient.execute(httpget);
				entity = response.getEntity();
				IOUtils.copy(entity.getContent(), fos);
			}
			catch (IOException e)
			{
				if (entity != null)
				{
					try
					{
						entity.consumeContent();
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
			finally
			{
				IOUtils.closeQuietly(fos);
			}
		}
	}

}
