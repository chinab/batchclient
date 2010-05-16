package com.vicutu.bw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Component;

import com.vicutu.bw.service.HttpClientService;
import com.vicutu.bw.vo.AccessDetail;

@Component("gipsAlpinHttpClientService")
public class GipsAlpinHttpClientServiceImpl extends DefaultHttpClientServiceImpl implements HttpClientService {

	@Override
	protected boolean login(DefaultHttpClient httpClient, AccessDetail accessDetail) throws Exception {
		HttpPost httpost = new HttpPost(accessDetail.getLoginUrl());
		HttpGet httpget = new HttpGet(accessDetail.getLoginRefreshUrl());
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("flduser", accessDetail.getLoginUsername()));
		nvps.add(new BasicNameValuePair("fldpwd", accessDetail.getLoginPassword()));

		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		HttpResponse response = httpClient.execute(httpost);

		response.getEntity().consumeContent();

		response = httpClient.execute(httpget);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return true;
		} else {
			return false;
		}
	}
}
