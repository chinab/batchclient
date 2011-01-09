package com.vicutu.bw.support.setup;

import java.util.List;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vicutu.bw.vo.AccessDetail;
import com.vicutu.commons.lang.IdentifierUtils;

public class MembaseSetup {

	private MemcachedClient memcachedClient;

	private List<AccessDetail> setupAccessDetails;

	public void setSetupAccessDetails(List<AccessDetail> setupAccessDetails) {
		this.setupAccessDetails = setupAccessDetails;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setup() throws Exception {
		for (AccessDetail accessDetail : setupAccessDetails) {
			memcachedClient.set(accessDetail.getName(), 0, accessDetail);
		}
	}

	public void clear() throws Exception {
		for (AccessDetail accessDetail : setupAccessDetails) {
			memcachedClient.delete(accessDetail.getName());
		}
	}

	public void init() {
		for (AccessDetail accessDetail : setupAccessDetails) {
			accessDetail.setId(IdentifierUtils.getIdentifier());
		}
	}

	public void printConfigFromLocal() {
		for (AccessDetail accessDetail : setupAccessDetails) {
			System.err.println(accessDetail);
		}
	}

	public void printConfigFromServer() throws Exception {
		for (AccessDetail accessDetail : setupAccessDetails) {
			System.err.println(memcachedClient.get(accessDetail.getName()));
		}
	}

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath:com/vicutu/bw/support/setup/ctx-membase-setup.xml" });
		ctx.registerShutdownHook();

		MembaseSetup membaseSetup = ctx.getBean("membaseSetup", MembaseSetup.class);

		try {
			membaseSetup.printConfigFromLocal();
			membaseSetup.setup();
			membaseSetup.printConfigFromServer();
			//			membaseSetup.clear();
			//			membaseSetup.printConfigFromServer();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ctx.destroy();
		}
	}
}
