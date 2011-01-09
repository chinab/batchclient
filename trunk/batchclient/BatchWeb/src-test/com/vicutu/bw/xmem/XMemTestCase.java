package com.vicutu.bw.xmem;

import net.rubyeye.xmemcached.MemcachedClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.commons.lang.IdentifierUtils;
import com.vicutu.commons.test.LoggedSpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "ctx-xmem-test.xml" })
@RunWith(LoggedSpringJUnit4ClassRunner.class)
public class XMemTestCase extends AbstractJUnit4SpringContextTests {

	private MemcachedClient memcachedClient;

	@Autowired
	@Qualifier("memcachedClient")
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Test
	public void test_setAndGet() throws Exception {
		AccessDetail accessDetail1 = this.prepareObject();
		logger.info(accessDetail1);
		memcachedClient.set(accessDetail1.getName(), 0, accessDetail1);
		AccessDetail accessDetail2 = memcachedClient.get(accessDetail1.getName());
		logger.info(accessDetail2);
	}
	
	@Test
	public void test_get() throws Exception {
		AccessDetail accessDetail2 = memcachedClient.get("CastedMemories");
		logger.info(accessDetail2);
	}

	private AccessDetail prepareObject() {
		AccessDetail accessDetail = new AccessDetail();
		accessDetail.setId(IdentifierUtils.getIdentifier());
		accessDetail.setName("CastedMemories");
		accessDetail.setHomePage("http://www.castedmemories.com/");
		accessDetail.setBaseUrl("http://castedmemories.com/members/index.htm");

		accessDetail.setUseProxy(false);
		accessDetail.setProxyHost("dl-proxyall.neusoft.com");
		accessDetail.setProxyPort(8080);
		accessDetail.setUseLogin(false);
		accessDetail.setProxyUsername("dipengfei");
		accessDetail.setProxyPassword("victu1982");

		accessDetail.setUseAuthorization(true);
		accessDetail.setAuthorizationUsername("v*$UbB");
		accessDetail.setAuthorizationPassword("csvn@Z");

		accessDetail.setSearchUrl("");
		accessDetail.setSavePath("J:/sec/cast/CastedMemories/picture");

		accessDetail.setQueueLength(100);
		accessDetail.setSingleHttpClient(true);

		accessDetail.setInterval(-1);
		accessDetail.setReplaceExist(false);

		accessDetail.setAvailble(false);

		return accessDetail;
	}
}
