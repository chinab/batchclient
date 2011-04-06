package com.vicutu.commons.lang;

import java.net.InetAddress;

public final class IdentifierUtils {
	private static long lastActive = 0L;

	private static short counter = 0;

	private IdentifierUtils() {
	}

	protected static char[] getIpAddress() {
		short ip = 0;
		try {
			String address = InetAddress.getLocalHost().getHostAddress();
			int pos = address.lastIndexOf('.');
			if (pos > 0) {
				address = address.substring(pos + 1);
				ip = Short.parseShort(address);
			}
		} catch (Exception ex) {
			ip = 0;
		}

		if (ip == 0) {
			return null;
		} else {
			char[] desc = getCharArray(8);
			char[] src = Integer.toBinaryString(ip).toCharArray();
			copyArray(src, desc);
			return desc;
		}
	}

	protected static char[] getCharArray(int length) {
		char[] array = new char[length];
		for (int i = 0; i < array.length; i++) {
			array[i] = '0';
		}
		return array;
	}

	protected static void copyArray(char[] src, char[] desc) {
		int srcPos = 0;
		int destPos = 0;
		int length = src.length;

		if (src.length <= desc.length) {
			destPos = desc.length - src.length;
		} else {
			srcPos = src.length - desc.length;
			length = desc.length;
		}

		System.arraycopy(src, srcPos, desc, destPos, length);
	}

	protected static synchronized long[] getCurrentTime() {
		long[] time = new long[2];

		long now = System.currentTimeMillis();

		if (now == lastActive) {
			counter++;
			if (counter > 255) {
				for (; now == lastActive; now = System.currentTimeMillis()) {
					try {
						Thread.sleep(1L);
					} catch (InterruptedException interruptedexception) {
					}
				}
				counter = 0;
			}
		} else {
			counter = 0;
			lastActive = now;
		}

		time[0] = now;
		time[1] = counter;

		return time;
	}

	public static long getIdentifier() {
		char[] data = getCharArray(63);

		char[] address = getIpAddress();
		long[] tmp = getCurrentTime();

		char[] count = null;
		char[] time = Long.toBinaryString(tmp[0]).toCharArray();
		if (tmp[1] != 0) {
			char[] desc = getCharArray(8);
			char[] src = Long.toBinaryString(tmp[1]).toCharArray();
			copyArray(src, desc);
			count = desc;
		}

		System.arraycopy(time, 0, data, 0, time.length);
		System.arraycopy(address, 0, data, data.length - 8, address.length);
		if (count != null) {
			System.arraycopy(count, 0, data, data.length - 16, count.length);
		}

		return Long.parseLong(new String(data), 2);
	}
}