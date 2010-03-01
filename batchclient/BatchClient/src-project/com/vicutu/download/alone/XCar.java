package com.vicutu.download.alone;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;

import com.vicutu.commons.lang.FileUtils;

public class XCar
{

	private static final String BASE_URL = "http://www.xcar.com.cn/bbs/viewthread.php?tid=10742387&extra=&showthread=&page=";

	private static final int FROM = 1;

	private static final int TO = 14;

	private static final String SAVE_PATH = "J:/sec/leg/temp/";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		initClient(httpclient);
		login(httpclient);

		for (int i = FROM; i <= TO; i++)
		{
			String linkUrl = BASE_URL + i;
			String htmlStr = getHtmlStr(httpclient, linkUrl);
			List<String> imageUrls = getImageUrl(htmlStr);
			download(httpclient,imageUrls);
		}

		httpclient.getConnectionManager().shutdown();
	}

	private static void login(DefaultHttpClient httpclient) throws Exception
	{
		HttpPost httpost = new HttpPost("http://reg.xcar.com.cn/logging.php?action=login");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("referer", "http://www.xcar.com.cn/"));
		nvps.add(new BasicNameValuePair("loginsubmit", "会员登录"));
		nvps.add(new BasicNameValuePair("loginfield", "username"));
		nvps.add(new BasicNameValuePair("username", "avaya123"));
		nvps.add(new BasicNameValuePair("password", "avaya123"));

		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		HttpResponse response = httpclient.execute(httpost);
		HttpEntity entity = response.getEntity();

		System.out.println("Login form get: " + response.getStatusLine());
		if (entity != null)
		{
			entity.consumeContent();
		}

		System.out.println("Post logon cookies:");
		List<Cookie> cookies = httpclient.getCookieStore().getCookies();
		if (cookies.isEmpty())
		{
			System.out.println("None");
		}
		else
		{
			for (int i = 0; i < cookies.size(); i++)
			{
				System.out.println("- " + cookies.get(i).toString());
			}
		}
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
			return EntityUtils.toString(entity, "gb2312");
		}
		return null;
	}

	private static List<String> getImageUrl(String htmlStr) throws Exception
	{
		List<String> imageUrls = new ArrayList<String>();

		Parser parser = new Parser();
		parser.setInputHTML(htmlStr);
		NodeList nla = parser.extractAllNodesThatMatch(new TagNameFilter("img"));
		for (int i = 0; i < nla.size(); i++)
		{
			Node node = nla.elementAt(i);
			if (node instanceof ImageTag)
			{
				ImageTag it=(ImageTag)node;
				String linkUrl0 = it.getImageURL();
				if(linkUrl0.contains("attachment.php"))
				{
					if(!linkUrl0.startsWith("http"))
					{
						linkUrl0 = "http://www.xcar.com.cn/bbs/" +linkUrl0;
					}
					imageUrls.add(linkUrl0);
				}
			}
		}
		
		return imageUrls;
	}

	private static void download(DefaultHttpClient httpclient,List<String> imageUrls)
	{
		FileOutputStream fos=null;
		HttpGet httpget = null;
		HttpEntity entity = null;
		HttpResponse response = null;
		for(int i=0;i<imageUrls.size();i++)
		{
			String imageUrl = imageUrls.get(i);
			String fileName= StringUtils.substringBefore(StringUtils.substringAfterLast(imageUrl, "aid="), "&");
			try
			{
				fos = FileUtils.openOutputStream(new File(SAVE_PATH,fileName+".jpg"));
				httpget = new HttpGet(imageUrl);
				response = httpclient.execute(httpget);
				entity = response.getEntity();
				IOUtils.copy(entity.getContent(), fos);
			}
			catch (IOException e)
			{
				if(entity!=null)
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
			}finally
			{
				IOUtils.closeQuietly(fos);
			}
		}
	}
}
